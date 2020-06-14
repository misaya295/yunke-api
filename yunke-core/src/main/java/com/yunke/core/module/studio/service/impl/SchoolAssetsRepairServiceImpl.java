package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.SchoolAssetsRepair;
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

}