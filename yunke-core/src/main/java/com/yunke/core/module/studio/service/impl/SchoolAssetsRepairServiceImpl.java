package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.SchoolAssets;
import com.yunke.common.core.entity.studio.SchoolAssetsRepair;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.mapper.SchoolAssetsRepairMapper;
import com.yunke.core.module.studio.service.ISchoolAssetsRepairService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 维修信息表 (SchoolAssetsRepair)表服务实现类
 *
 * @author chachae
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
        return baseMapper.pageschoolAssetsRepairDetail(page,schoolAssetsRepair);
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
}