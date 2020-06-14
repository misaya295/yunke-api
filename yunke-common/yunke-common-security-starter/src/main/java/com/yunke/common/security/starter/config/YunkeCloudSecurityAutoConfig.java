package com.yunke.common.security.starter.config;

import com.yunke.common.security.starter.handler.YunkeAccessDeniedHandler;
import com.yunke.common.security.starter.handler.YunkeAuthExceptionEntryPoint;
import com.yunke.common.security.starter.properties.YunkeCloudSecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author chachae
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(YunkeCloudSecurityProperties.class)
@ConditionalOnProperty(value = "yunke.security.enable", havingValue = "true", matchIfMissing = true)
public class YunkeCloudSecurityAutoConfig {

  @Bean
  @ConditionalOnMissingBean(name = "accessDeniedHandler")
  public YunkeAccessDeniedHandler accessDeniedHandler() {
    return new YunkeAccessDeniedHandler();
  }

  @Bean
  @ConditionalOnMissingBean(name = "authenticationEntryPoint")
  public YunkeAuthExceptionEntryPoint authenticationEntryPoint() {
    return new YunkeAuthExceptionEntryPoint();
  }

  @Bean
  @ConditionalOnMissingBean(value = PasswordEncoder.class)
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
