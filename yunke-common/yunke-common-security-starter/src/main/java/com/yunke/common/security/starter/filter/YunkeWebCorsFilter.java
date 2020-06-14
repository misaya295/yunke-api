package com.yunke.common.security.starter.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.cors.CorsConfiguration;

/**
 * @author chachae
 * @version v1.0
 * @date 2020/6/13 23:04
 */
public class YunkeWebCorsFilter implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", CorsConfiguration.ALL);
    response.setHeader("Access-Control-Allow-Methods", CorsConfiguration.ALL);
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Headers", HttpHeaders.AUTHORIZATION.toLowerCase());
    if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
      response.setStatus(HttpStatus.OK.value());
    } else {
      filterChain.doFilter(req, res);
    }
  }
}
