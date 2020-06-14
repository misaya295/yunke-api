package com.yunke.core;

import com.yunke.common.security.starter.annotation.EnableYunkeResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 核心系统启动器
 *
 * @author chachae
 * @version v1.0
 * @date 2020/6/12 10:33
 */
@EnableAsync
@SpringBootApplication
@EnableYunkeResourceServer
@EnableTransactionManagement
@MapperScan("com.yunke.core.module.*.mapper")
public class YunkeCoreApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(YunkeCoreApplication.class)
        .web(WebApplicationType.SERVLET)
        .run(args);
  }
}

