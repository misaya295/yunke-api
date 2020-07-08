package com.yunke.core.module.oss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.oss.QiNiuConfig;
import com.yunke.core.module.oss.mapper.QiNiuConfigMapper;
import com.yunke.core.module.oss.service.IQiNiuConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 七牛云配置表服务实现类
 *
 * @author chachae
 * @since 2020-06-27 13:21:50
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QiNiuConfigServiceImpl extends ServiceImpl<QiNiuConfigMapper, QiNiuConfig> implements
    IQiNiuConfigService {

  @Override
  public QiNiuConfig getConfig() {
    return baseMapper.selectById(1L);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateConfig(QiNiuConfig qiNiuConfig) {
    qiNiuConfig.setConfigId(1L);
    baseMapper.deleteById(1L);
    baseMapper.insert(qiNiuConfig);
  }
}