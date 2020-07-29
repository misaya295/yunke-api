package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Copyright;
import com.yunke.common.core.entity.studio.Items;
import com.yunke.common.core.entity.studio.Members;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.generator.IdGenerateUtil;
import com.yunke.core.module.studio.generator.TaskTypeConstant;
import com.yunke.core.module.studio.mapper.CopyrightMapper;
import com.yunke.core.module.studio.service.ICopyrightService;
import com.yunke.core.module.studio.service.IItemsService;
import com.yunke.core.module.studio.service.IMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTask(Copyright copyright, String[] userId, String[] state) {
        //生成带任务类型的id
        copyright.setCopyrightId(IdGenerateUtil.nextId(TaskTypeConstant.COPYRIGHT));
        //首先判断，要绑定的项目是否存在
        Items items = this.itemsService.getById(copyright.getItemId());
        if(items!=null){
            //保存任务
            this.save(copyright);
        }else{
            throw new ApiException("不存在该项目，无法申请");
        }
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
        if (copyright.getState() == 1||(copyright.getReimbursement()!=null&&copyright.getReimbursement()==1)) {
            if(copyright.getItemId()!=null||copyright.getItemId()!=""){
                //首先判断，要绑定的项目是否存在
                Items items = this.itemsService.getById(copyright.getItemId());
                if(items!=null){
                    this.updateById(copyright); //修改任务
                }else{
                    throw new ApiException("不存在该项目，无法申请");
                }
            }else{
                    this.updateById(copyright); //修改任务
            }



        }

    }

    @Override
    public IPage<Copyright> pageTaskList(QueryParam param, Copyright copyright) {
        Page<Copyright> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "title", SystemConstant.ORDER_ASC, true);
        return baseMapper.pageTask(page,copyright);
    }

    @Override
    public Map<String, Object> getTask(String copyrightId) {
        return baseMapper.getTask(copyrightId);
    }

    @Override
    public Integer getAllTaskCount() {
        return this.count();
    }



}