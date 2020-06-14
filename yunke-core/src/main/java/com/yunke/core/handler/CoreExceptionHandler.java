package com.yunke.core.handler;

import com.yunke.common.core.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 服务异常处理
 *
 * @author chachae
 * @since 2020/4/24 21:25
 */

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class CoreExceptionHandler extends BaseExceptionHandler {

}
