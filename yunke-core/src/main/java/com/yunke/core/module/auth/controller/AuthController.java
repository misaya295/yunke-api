package com.yunke.core.module.auth.controller;

import com.yunke.common.core.entity.R;
import com.yunke.core.module.auth.service.ICaptchaService;
import java.security.Principal;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chachae
 * @date 2020/4/25 17:42
 */
@Slf4j
@Validated
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

  private final ICaptchaService captchaService;

  @GetMapping("user")
  public Principal currentUser(Principal principal) {
    return principal;
  }

  @GetMapping("captcha")
  public R<String> getCaptcha(HttpServletResponse response) {
    return R.ok(captchaService.create(response));
  }

}
