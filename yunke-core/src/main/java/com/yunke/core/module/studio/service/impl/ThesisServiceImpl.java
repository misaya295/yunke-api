package com.yunke.core.module.studio.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Members;
import com.yunke.common.core.entity.studio.Thesis;
import com.yunke.common.core.util.DateUtil;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.generator.IdGenerateUtil;
import com.yunke.core.module.studio.generator.TaskTypeConstant;
import com.yunke.core.module.studio.mapper.ThesisMapper;
import com.yunke.core.module.studio.service.IMembersService;
import com.yunke.core.module.studio.service.IThesisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * 论文_任务表(Thesis)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ThesisServiceImpl extends ServiceImpl<ThesisMapper, Thesis> implements IThesisService {

    private final IMembersService membersService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTask(Thesis thesis, String[] userId, String[] state) {
        //生成带任务类型的id
        thesis.setThesisId(IdGenerateUtil.nextId(TaskTypeConstant.THESIS));
        //保存当前时间
        thesis.setTime(DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_UNIFIED_PATTERN));
        //保存任务
        this.save(thesis);

        //添加成员
        ArrayList<Members> members = new ArrayList<>(userId.length);
        IntStream.range(0, userId.length).forEach(index -> {
            members.add(new Members(Integer.parseInt(userId[index]), Integer.parseInt(state[index]), thesis.getThesisId()));
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
    public void updateTask(Thesis thesis) {
        //进行中的任务or报销成功后的报销字段修改
        if (thesis.getState() == 1) {
            //更新时间
//            thesis.setTime(DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_UNIFIED_PATTERN));
            this.updateById(thesis); //修改论文任务 ++-
            if(thesis.getUserId()!=null&&thesis.getUserId()!="") {
                //修改成员
                //1,先删除原先成员列表
                this.membersService.remove(new LambdaQueryWrapper<Members>().eq(Members::getTaskId, thesis.getThesisId()));
                //添加成员
                String[] userId = thesis.getUserId().split(StrUtil.COMMA);
                String[] state = thesis.getM_state().split(StrUtil.COMMA);
                ArrayList<Members> members = new ArrayList<>(userId.length);
                IntStream.range(0, userId.length).forEach(index -> {
                    members.add(new Members(Integer.parseInt(userId[index]), Integer.parseInt(state[index]), thesis.getThesisId()));
                });
                this.membersService.saveBatch(members);
            }
        }else if(thesis.getState() == 2){
            Thesis thesis1 = new Thesis();
            thesis1.setThesisId(thesis.getThesisId());
            if(thesis.getReimbursement()!=null&&thesis.getReimbursement()!=-1){
                thesis1.setReimbursement(thesis.getReimbursement());
                this.updateById(thesis1);
            }

        }

    }

    @Override
    public IPage<Thesis> pageTaskList(QueryParam param, Thesis thesis) {
        Page<Thesis> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "title", SystemConstant.ORDER_ASC, true);
        IPage<Thesis> thesisIPage = baseMapper.pageTask(page, thesis);
        List<Thesis> records = thesisIPage.getRecords();
        records.stream().forEach(thesis1 -> {
            List<Members> members = this.membersService.getMembers(thesis1.getThesisId());
            if (members!=null){
                thesis1.setMembers(members);
            }
        });
        thesisIPage.setRecords(records);
        return thesisIPage;
    }

    @Override
    public Thesis getTask(String thesisId) {
        Thesis thesis = baseMapper.getTask(thesisId);
        List<Members> members = this.membersService.getMembers(thesisId);
        if (members!=null){
            thesis.setMembers(members);
        }
        return thesis;
    }

    @Override
    public Integer getAllTaskCount() {
        return this.count();
    }

    @Override
    public void updateState(Thesis thesis) {
            this.baseMapper.updateState(thesis);
    }


}