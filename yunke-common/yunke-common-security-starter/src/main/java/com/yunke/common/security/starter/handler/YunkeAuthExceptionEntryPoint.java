package com.yunke.common.security.starter.handler;

import com.yunke.common.core.entity.R;
import com.yunke.common.core.util.HttpUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author chachae
 */
@Slf4j
public class YunkeAuthExceptionEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    String requestUri = request.getRequestURI();
    int status = HttpServletResponse.SC_UNAUTHORIZED;
    String message = "访问令牌不合法";
    log.error("客户端访问{}请求失败: {}", requestUri, message, authException);
    HttpUtil.makeJsonResponse(response, status, R.fail(message));
  }
}
