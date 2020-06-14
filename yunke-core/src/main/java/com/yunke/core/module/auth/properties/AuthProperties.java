package com.yunke.core.module.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author chachae
 * @since 2020/4/24 23:43
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:yunke-auth.properties"})
@ConfigurationProperties(prefix = "yunke.auth")
public class AuthProperties {

  /**
   * JWT加签密钥
   */
  private String jwtAccessKey;
  /**
   * 是否使用 JWT令牌
   */
  private Boolean enableJwt;

  /**
   * 社交登录所使用的 Client
   */
  private String socialLoginClientId;

  /**
   * 验证码
   */
  private CaptchaProperties code = new CaptchaProperties();
}
