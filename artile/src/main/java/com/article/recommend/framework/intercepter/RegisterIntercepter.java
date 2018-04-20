package com.article.recommend.framework.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class RegisterIntercepter extends WebMvcConfigurerAdapter {
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("注册");
        registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/dictionary/**","/menu/**","/quartz/**","/rechistory/**","/recresult/**");//配置登录拦截器拦截路径
        super.addInterceptors(registry);
    }
}
