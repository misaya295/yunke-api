package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.Orientation;
import com.yunke.core.module.studio.mapper.OrientationMapper;
import com.yunke.core.module.studio.service.IOrientationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 方向表(Orientation)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrientationServiceImpl extends ServiceImpl<OrientationMapper, Orientation> implements
    IOrientationService {

}