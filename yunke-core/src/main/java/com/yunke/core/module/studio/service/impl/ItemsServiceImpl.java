package com.yunke.core.module.studio.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Items;
import com.yunke.common.core.entity.studio.Members;
import com.yunke.common.core.entity.system.SystemUser;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.SortUtil;
import com.yunke.common.core.util.UserIdUtil;
import com.yunke.core.module.studio.generator.IdGenerateUtil;
import com.yunke.core.module.studio.generator.TaskTypeConstant;
import com.yunke.core.module.studio.mapper.ItemsMapper;
import com.yunke.core.module.studio.service.IItemsService;
import com.yunke.core.module.studio.service.IMembersService;
import com.yunke.core.module.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 项目_任务表(Items)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements IItemsService {

    private final IMembersService membersService;
    private final IUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTask(Items items, String[] userId, String[] state) {
        //生成带任务类型的id
        items.setItemsId(IdGenerateUtil.nextId(TaskTypeConstant.ITEMS));
        //保存任务
        this.save(items);
        //添加成员
        ArrayList<Members> members = new ArrayList<>(userId.length);
        IntStream.range(0, userId.length).forEach(index -> {
            members.add(new Members(Integer.parseInt(userId[index]), Integer.parseInt(state[index]), items.getItemsId()));
        });
        this.membersService.saveBatch(members);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTask(String[] ids) {
        //删除任务
        //先判断项目是否被软件著作权绑定
        Stream.of(ids).forEach( id ->
                {
                    if (baseMapper.getCopyright(id)!=null) {
                        throw new ApiException("项目已经与软件著作权关联，请先删除相关软件著作权任务");
                    } else {
                        this.removeById(id);
                    }
                }
        );
        //删除成员
        Stream.of(ids).forEach(id -> this.membersService.remove(new LambdaQueryWrapper<Members>().eq(Members::getTaskId, id)));


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTask(Items items) {
        //进行中的任务
        if (items.getState() == 1) {

            this.updateById(items); //修改任务
            if(items.getUserId()!=null&&items.getUserId()!="") {
                //修改成员
                //1,先删除原先成员列表
                this.membersService.remove(new LambdaQueryWrapper<Members>().eq(Members::getTaskId, items.getItemsId()));
                //添加成员
                String[] userId = items.getUserId().split(StrUtil.COMMA);
                String[] state = items.getM_state().split(StrUtil.COMMA);
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
                    members.add(new Members(Integer.parseInt(userId[index]), Integer.parseInt(state[index]), items.getItemsId()));
                });
                this.membersService.saveBatch(members);
            }
        }else if(items.getState() == 2){  //已完成的任务
            Items items1 = new Items();
            items1.setItemsId(items.getItemsId());
            if(items.getReimbursement()!=null&&items.getReimbursement()!=-1){
                items1.setReimbursement(items.getReimbursement());
                this.updateById(items1);
            }

        }

    }

    @Override
    public IPage<Items> pageTaskList(QueryParam param, Items items) {
        Page<Items> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "title", SystemConstant.ORDER_ASC, true);
        IPage<Items> itemsIPage = baseMapper.pageTask(page, items);
        List<Items> records = itemsIPage.getRecords();
        records.stream().forEach(items1 -> {
            List<Members> members = this.membersService.getMembers(items1.getItemsId());
            if (members!=null){
                items1.setMembers(members);
            }
        });
        itemsIPage.setRecords(records);
        return itemsIPage;
    }

    @Override
    public Items getTask(String itemsId) {
        Items items = baseMapper.getTask(itemsId);
        if(items!=null){
            List<Members> members = this.membersService.getMembers(itemsId);
            if (members!=null){
                items.setMembers(members);
            }

        }
        return items;
    }

    @Override
    public Integer getAllTaskCount() {
        return this.count();
    }

    @Override
    public void updateState(Items items) {
        this.baseMapper.updateState(items);
    }

}