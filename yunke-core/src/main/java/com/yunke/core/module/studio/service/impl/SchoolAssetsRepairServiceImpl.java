package com.yunke.core.module.studio.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.SchoolAssets;
import com.yunke.common.core.entity.studio.SchoolAssetsRepair;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.mapper.SchoolAssetsRepairMapper;
import com.yunke.core.module.studio.service.ISchoolAssetsRepairService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 维修信息表 (SchoolAssetsRepair)表服务实现类
 *
 * @author Pning
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolAssetsRepairServiceImpl extends
    ServiceImpl<SchoolAssetsRepairMapper, SchoolAssetsRepair> implements
    ISchoolAssetsRepairService {

    @Override
    public IPage<SchoolAssetsRepair> pageSchoolAssetsRepair(QueryParam param, SchoolAssetsRepair schoolAssetsRepair) {
        Page<SchoolAssets> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "id", SystemConstant.ORDER_ASC, true);
        String statrtTime = null;//查询开始时间
        String endTime = null;//查询结束时间
        if(schoolAssetsRepair.getRepairDate()!=null&&(!"".equals(schoolAssetsRepair.getRepairDate()))){ //funding存放的日期不为空
            String[] split_time = StrUtil.split(schoolAssetsRepair.getRepairDate(), StrUtil.COMMA);
            if(split_time.length==2){
                statrtTime = split_time[0];
                endTime = split_time[1];
            }
        }
        return baseMapper.pageschoolAssetsRepairDetail(page,schoolAssetsRepair,statrtTime,endTime);
    }

    @Override
    public void deleteSchoolAssetsRepairById(int[] schoolAssetsRepairIds) {
        baseMapper.deleteSchoolAssetsRepairById(schoolAssetsRepairIds);
    }

    @Override
    public void addSchoolAssetsRepair(SchoolAssetsRepair schoolAssetsRepair) {
        baseMapper.addSchoolAssetsRepair(schoolAssetsRepair);
    }

    @Override
    public void updateSchoolAssetsRepairsMessage(SchoolAssetsRepair schoolAssetsRepair) {
        baseMapper.updateSchoolAssetsRepairsMessage(schoolAssetsRepair);
    }

    @Override
    public SchoolAssetsRepair selectSchoolAssetsRepairIdById(int schoolAssetsRepairId) {
        return baseMapper.selectSchoolAssetsRepairIdById(schoolAssetsRepairId);
    }

    @Override
    public void updateSchoolAssetsRepairsState(SchoolAssetsRepair schoolAssetsRepair) {
        baseMapper.updateSchoolAssetsRepairsState(schoolAssetsRepair);
    }
}