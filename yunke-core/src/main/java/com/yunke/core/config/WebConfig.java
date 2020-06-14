package com.yunke.core.config;

import com.yunke.common.core.constant.SystemConstant;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMVC 配置
 *
 * @author chachae
 * @version v1.0
 * @date 2020/5/30 10:02
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * 注册异步线程池
   */
  @Bean(SystemConstant.ASYNC_POOL)
  public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(20);
    executor.setQueueCapacity(100);
    executor.setKeepAliveSeconds(30);
    executor.setThreadNamePrefix("yunke-async-thread");
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setAwaitTerminationSeconds(60);
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.initialize();
    return executor;
  }
}
