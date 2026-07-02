package com.alibaba.tqn.web;

import com.alibaba.tqn.web.interceptor.ApiInterceptor;
import com.alibaba.tqn.web.interceptor.SystemInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public SystemInterceptor systemInterceptor() {
        return new SystemInterceptor();
    }

    @Bean
    public ApiInterceptor apiInterceptor() {
        return new ApiInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(systemInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns("/public/**");

        registry.addInterceptor(apiInterceptor())
            .addPathPatterns("/api/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}
