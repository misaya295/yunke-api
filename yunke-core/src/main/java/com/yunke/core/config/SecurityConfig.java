package com.yunke.core.config;

import com.yunke.common.core.constant.EndpointConstant;
import com.yunke.core.handler.YunkeWebLoginFailureHandler;
import com.yunke.core.handler.YunkeWebLoginSuccessHandler;
import com.yunke.core.module.auth.filter.ValidateCodeGrantFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * WebSecurity 配置
 *
 * @author chachae
 * @since 2020/04/21
 */
@Order(2)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final YunkeWebLoginSuccessHandler successHandler;
  private final YunkeWebLoginFailureHandler failureHandler;
  private final ValidateCodeGrantFilter validateCodeGrantFilter;
  private final UserDetailsService userDetailService;
  private final PasswordEncoder passwordEncoder;


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().and()
        .addFilterBefore(validateCodeGrantFilter, UsernamePasswordAuthenticationFilter.class)
        .requestMatchers()
        .antMatchers(EndpointConstant.OAUTH_ALL, EndpointConstant.LOGIN)
        .and()
        .authorizeRequests()
        .antMatchers(EndpointConstant.OAUTH_ALL).authenticated()
        .and()
        .formLogin()
        .loginPage(EndpointConstant.LOGIN)
        .loginProcessingUrl(EndpointConstant.LOGIN)
        .successHandler(successHandler)
        .failureHandler(failureHandler)
        .permitAll()
        .and().csrf().disable()
        .httpBasic().disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
  }
}
