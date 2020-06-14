package com.yunke.core.filter;

import com.yunke.common.security.starter.filter.YunkeWebCorsFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 跨域请求过滤器
 *
 * @author chachae
 * @version v1.0
 * @date 2020/6/14 00:10
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebCorsFilter extends YunkeWebCorsFilter {

}
