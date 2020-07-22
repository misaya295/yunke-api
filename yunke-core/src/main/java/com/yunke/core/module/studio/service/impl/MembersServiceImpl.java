package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Members;
import com.yunke.common.core.entity.studio.Thesis;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.mapper.MembersMapper;
import com.yunke.core.module.studio.service.IMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 任务成员的中间表(Members)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MembersServiceImpl extends ServiceImpl<MembersMapper, Members> implements
        IMembersService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMembers(String taskId, String[] userId) {
        //任务成员更新
        //1,先删除原先成员列表
        this.remove(new LambdaQueryWrapper<Members>().eq(Members::getState, 2).eq(Members::getTaskId, taskId));
        //2,添加成员列表
        setMembers(taskId, userId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCharge(Members members) {

        //1,先去除成员身份
        this.remove(new LambdaQueryWrapper<Members>().eq(Members::getState, 2)
                .eq(Members::getUserId, members.getUserId())
                .eq(Members::getTaskId, members.getTaskId()));
        //2,再去除原先负责人
        this.remove(new LambdaQueryWrapper<Members>().eq(Members::getState, 1)
                .eq(Members::getTaskId, members.getTaskId()));
        //3,添加负责人
        this.save(members);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMembers(String taskId, String[] userId) {
        Stream.of(userId).forEach(id -> this.remove(new LambdaQueryWrapper<Members>()
                .eq(Members::getTaskId, taskId)
                .eq(Members::getUserId, id)));
    }

    @Override
    public IPage<Map<String, Object>> pageMembersList(QueryParam param, String taskId) {
        Page<Map<String,Object>> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "task_id", SystemConstant.ORDER_ASC, true);
        return baseMapper.pageMembers(page,taskId);
    }

    //添加成员列表
    public void setMembers(String taskId, String[] userId) {
        ArrayList<Members> members = new ArrayList<>(userId.length);
        Stream.of(userId).forEach(id -> members.add(new Members(Integer.parseInt(id), 2, taskId)));
        this.saveBatch(members);
    }
}