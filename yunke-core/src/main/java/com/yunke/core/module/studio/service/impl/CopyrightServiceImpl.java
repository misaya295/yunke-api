package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.Copyright;
import com.yunke.core.module.studio.mapper.CopyrightMapper;
import com.yunke.core.module.studio.service.ICopyrightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 软件著作权_任务表(Copyright)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CopyrightServiceImpl extends ServiceImpl<CopyrightMapper, Copyright> implements
    ICopyrightService {

}