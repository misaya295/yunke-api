package com.yunke.core.module.oss.controller;

import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.oss.QiNiuConfig;
import com.yunke.core.module.oss.service.IQiNiuConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 七牛云配置
 *
 * @author chachae
 * @version v1.0
 * @date 2020/6/27 14:18
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("oss/config")
public class QiNiuConfigController {

  private final IQiNiuConfigService qiNiuConfigService;

  @GetMapping
  public R<QiNiuConfig> getConfig() {
    return R.ok(qiNiuConfigService.getConfig());
  }

  @PutMapping
  public void updateConfig(@Validated QiNiuConfig qiniuConfig) {
    qiNiuConfigService.updateConfig(qiniuConfig);
  }


}
