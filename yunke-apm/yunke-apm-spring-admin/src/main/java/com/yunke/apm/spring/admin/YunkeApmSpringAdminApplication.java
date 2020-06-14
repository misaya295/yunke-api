package com.yunke.apm.spring.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Spring-Admin 服务监控启动器
 *
 * @author chachae
 * @since 2020/4/27 14:22
 */
@EnableAdminServer
@SpringBootApplication
public class YunkeApmSpringAdminApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(YunkeApmSpringAdminApplication.class)
        .web(WebApplicationType.SERVLET)
        .run(args);
  }

}
