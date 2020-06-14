package com.yunke.common.security.starter.handler;

import com.yunke.common.core.entity.R;
import com.yunke.common.core.util.HttpUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author chachae
 */
public class YunkeAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException {
    HttpUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN,
        R.fail("没有权限访问该资源"));
  }
}
