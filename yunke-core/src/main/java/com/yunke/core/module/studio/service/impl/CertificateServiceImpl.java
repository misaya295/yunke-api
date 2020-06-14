package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.Certificate;
import com.yunke.core.module.studio.mapper.CertificateMapper;
import com.yunke.core.module.studio.service.ICertificateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资格证表(Certificate)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements
    ICertificateService {

}