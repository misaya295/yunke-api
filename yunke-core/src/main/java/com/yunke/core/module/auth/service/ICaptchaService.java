package com.yunke.core.module.auth.service;

import com.yunke.core.module.auth.exception.CaptchaException;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码接口
 *
 * @author chachae
 * @since 2020/4/25 23:04
 */
public interface ICaptchaService {

  /**
   * 创建验证码
   *
   * @param response 响应对象
   * @return base64 图像
   */
  String create(HttpServletResponse response) throws CaptchaException;

  /**
   * 校验验证码
   *
   * @param key   验证码key
   * @param value 用户输入值
   */
  void validateCode(String key, String value) throws CaptchaException;
}
