package com.yunke.core.module.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.yunke.common.core.constant.ImageTypeConstant;
import com.yunke.common.core.constant.ParamsConstant;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.util.HttpUtil;
import com.yunke.common.redis.starter.service.RedisService;
import com.yunke.core.module.auth.exception.CaptchaException;
import com.yunke.core.module.auth.properties.AuthProperties;
import com.yunke.core.module.auth.properties.CaptchaProperties;
import com.yunke.core.module.auth.service.ICaptchaService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * 验证码业务实现
 *
 * @author chachae
 * @since 2020/4/25 23:15
 */
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements ICaptchaService {

  private final RedisService redisService;
  private final AuthProperties properties;

  @Override
  public String create(HttpServletResponse response) throws CaptchaException {
    String key = HttpUtil.getParam(ParamsConstant.CAPTCHA_KEY);
    if (StrUtil.isBlank(key)) {
      throw new CaptchaException("验证码key不能为空");
    }
    CaptchaProperties code = properties.getCode();
    // 创建指定规格的验证码
    Captcha captcha = createCaptcha(code);
    // 设置响应头信息
    setHeader(response);
    // 缓存至 Redis
    redisService.set(SystemConstant.CAPTCHA_PREFIX + key, captcha.text(), code.getTime());
    return captcha.toBase64();
  }

  @Override
  public void validateCode(String key, String value) throws CaptchaException {
    Object rightCaptchaText = redisService.get(SystemConstant.CAPTCHA_PREFIX + key);
    redisService.del(SystemConstant.CAPTCHA_PREFIX + key);
    if (StrUtil.isBlank(value)) {
      throw new CaptchaException("请输入验证码");
    } else if (rightCaptchaText == null) {
      throw new CaptchaException("验证码已过期");
    } else if (!value.equalsIgnoreCase(String.valueOf(rightCaptchaText))) {
      throw new CaptchaException("验证码不正确");
    }
  }

  private Captcha createCaptcha(CaptchaProperties code) {
    Captcha captcha;
    if (code.getType().equalsIgnoreCase(ImageTypeConstant.GIF)) {
      captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
    } else if (code.getType().equalsIgnoreCase(ImageTypeConstant.PNG)) {
      captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
    } else {
      captcha = new ArithmeticCaptcha(code.getWidth(), code.getHeight(), code.getLength());
    }
    captcha.setCharType(code.getCharType());
    return captcha;
  }

  private void setHeader(HttpServletResponse response) {
    response.setHeader(HttpHeaders.PRAGMA, "No-cache");
    response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
    response.setDateHeader(HttpHeaders.EXPIRES, 0L);
  }

}
