package com.yunke.common.security.starter.properties;

import com.yunke.common.core.constant.EndpointConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author chachae
 */
@ConfigurationProperties(prefix = "yunke.security")
public class YunkeCloudSecurityProperties {

  /**
   * 是否开启安全配置
   */
  private Boolean enable;
  /**
   * 配置需要认证的uri，默认为所有/**
   */
  private String authUri = EndpointConstant.ALL;
  /**
   * 免认证资源路径，支持通配符 多个值时使用逗号分隔
   */
  private String anonUris;

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  public String getAuthUri() {
    return authUri;
  }

  public void setAuthUri(String authUri) {
    this.authUri = authUri;
  }

  public String getAnonUris() {
    return anonUris;
  }

  public void setAnonUris(String anonUris) {
    this.anonUris = anonUris;
  }

  @Override
  public String toString() {
    return "YunkeCloudSecurityProperties{" +
        "enable=" + enable +
        ", authUri='" + authUri + '\'' +
        ", anonUris='" + anonUris + '\'' +
        '}';
  }
}
