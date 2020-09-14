package com.yunke.core.module.studio.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Copyright;
import com.yunke.common.core.entity.studio.Items;
import com.yunke.common.core.entity.studio.Members;
import com.yunke.common.core.entity.system.SystemUser;
import com.yunke.common.core.util.SortUtil;
import com.yunke.common.core.util.UserIdUtil;
import com.yunke.core.module.studio.generator.IdGenerateUtil;
import com.yunke.core.module.studio.generator.TaskTypeConstant;
import com.yunke.core.module.studio.mapper.CopyrightMapper;
import com.yunke.core.module.studio.service.ICopyrightService;
import com.yunke.core.module.studio.service.IItemsService;
import com.yunke.core.module.studio.service.IMembersService;
import com.yunke.core.module.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 软件著作权_任务表(Copyright)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CopyrightServiceImpl extends ServiceImpl<CopyrightMapper, Copyright> implements
    ICopyrightService {

    private final IMembersService membersService;
    private final IItemsService itemsService;
    private final IUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTask(Copyright copyright, String[] userId, String[] state) {
        //生成带任务类型的id
        copyright.setCopyrightId(IdGenerateUtil.nextId(TaskTypeConstant.COPYRIGHT));
//        //首先判断，要绑定的项目是否存在
//        Items items = this.itemsService.getById(copyright.getItemId());
//        if(items!=null){
            //保存任务
            this.save(copyright);
//        }else{
//            throw new ApiException("不存在该项目，无法申请");
//        }
        //添加成员
        ArrayList<Members> members = new ArrayList<>(userId.length);
        IntStream.range(0, userId.length).forEach(index -> {
            members.add(new Members(Integer.parseInt(userId[index]), Integer.parseInt(state[index]), copyright.getCopyrightId()));
        });
        this.membersService.saveBatch(members);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTask(String[] ids) {
        //删除任务
        this.removeByIds(Arrays.asList(ids));
        //删除成员
        Stream.of(ids).forEach(id -> this.membersService.remove(new LambdaQueryWrapper<Members>().eq(Members::getTaskId, id)));


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTask(Copyright copyright) {
        //进行中的任务or报销成功后的报销字段修改
        if (copyright.getState() == 1) {
//            if(copyright.getItemId()!=null&&copyright.getItemId()!=""){
                //首先判断，要绑定的项目是否存在
//                Items items = this.itemsService.getById(copyright.getItemId());
//                if(items!=null){
//                    this.updateById(copyright); //修改任务
//                }else{
//                    throw new ApiException("不存在该项目，无法申请");
//                }
//            }else{
                    this.updateById(copyright); //修改任务
//            }
            if(copyright.getUserId()!=null&&copyright.getUserId()!=""){
                //修改成员
                //1,先删除原先成员列表
                this.membersService.remove(new LambdaQueryWrapper<Members>().eq(Members::getTaskId, copyright.getCopyrightId()));
                //添加成员
                String[] userId = copyright.getUserId().split(StrUtil.COMMA);
                String[] state = copyright.getM_state().split(StrUtil.COMMA);
                List<Integer> strangerIndex = UserIdUtil.formatStrangerName(userId);//非内部成员id的下标集合
                //给非内部人员注册一个禁用状态的账号
                for(int i=0;i<strangerIndex.size();i++) {
                    SystemUser user = UserIdUtil.strangerUser(userId[strangerIndex.get(i)]);//生成一个默认的用户
                    userService.createUser(user);
                    //将原本的非内置成员的不正常id替换为创建后的id
                    userId[strangerIndex.get(i)] = userService.getSystemUser(user.getUsername()).getUserId().toString();
                }
                ArrayList<Members> members = new ArrayList<>(userId.length);
                IntStream.range(0, userId.length).forEach(index -> {
                    members.add(new Members(Integer.parseInt(userId[index]), Integer.parseInt(state[index]), copyright.getCopyrightId()));
                });
                this.membersService.saveBatch(members);
            }
        }else if(copyright.getState() == 2){  //已完成的任务
            Copyright copyright1 = new Copyright();
            copyright1.setCopyrightId(copyright.getCopyrightId());
            if(copyright.getReimbursement()!=null&&copyright.getReimbursement()!=-1){
                copyright1.setReimbursement(copyright.getReimbursement());
                this.updateById(copyright1);
            }

        }

    }

    @Override
    public IPage<Copyright> pageTaskList(QueryParam param, Copyright copyright) {
        Page<Copyright> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "title", SystemConstant.ORDER_ASC, true);
        IPage<Copyright> copyrightIPage = baseMapper.pageTask(page, copyright);
        List<Copyright> records = copyrightIPage.getRecords();
        records.stream().forEach(copyright1 -> {
            List<Members> members = this.membersService.getMembers(copyright1.getCopyrightId());
            if (members!=null){
                copyright1.setMembers(members);
            }
        });
        copyrightIPage.setRecords(records);
        return copyrightIPage;
    }

    @Override
    public Copyright getTask(String copyrightId) {
        Copyright copyright = baseMapper.getTask(copyrightId);
        if(copyright!=null){
            List<Members> members = this.membersService.getMembers(copyrightId);
            if (members!=null){
                copyright.setMembers(members);
            }
        }

        return copyright;
    }

    @Override
    public Integer getAllTaskCount() {
        return this.count();
    }

    @Override
    public void updateState(Copyright copyright) {
        this.baseMapper.updateState(copyright);
    }


}