package com.yunke.core.module.auth.exception;

/**
 * 验证码异常
 *
 * @author chachae
 * @since 2020/4/25 23:05
 */
public class CaptchaException extends RuntimeException {

  private static final long serialVersionUID = -7264897109645766939L;

  public CaptchaException(String message) {
    super(message);
  }
}
