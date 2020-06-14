package com.yunke.core.module.studio.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.Postgraduate;
import com.yunke.core.module.studio.mapper.PostgraduateMapper;
import com.yunke.core.module.studio.service.IPostgraduateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考研人员表(Postgraduate)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PostgraduateServiceImpl extends
    ServiceImpl<PostgraduateMapper, Postgraduate> implements
    IPostgraduateService {

}