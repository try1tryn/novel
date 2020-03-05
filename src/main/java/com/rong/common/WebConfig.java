package com.rong.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description: 配置类
 * @author: QR
 * @create: 2020-01-05 17:15
 **/
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");

    }

    @Bean
    public GlobalInterceptor authenticationInterceptor() {
        return new GlobalInterceptor();
    }
}