package com.example.parking_manager_system.Configs;

import com.example.parking_manager_system.Interceptor.AdminInterceptor;
import com.example.parking_manager_system.Interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author SWQXDBA
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
       configurer.addPathPrefix("api", c -> true);
    }

    @Autowired
    UserInterceptor userInterceptor;

    @Autowired
    AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加对用户的拦截
        registry.addInterceptor(userInterceptor).addPathPatterns("api/user/**")
                .excludePathPatterns("api/user/login/**").excludePathPatterns("api/user/register/**");

        //添加对管理员的拦截
        registry.addInterceptor(adminInterceptor).addPathPatterns("api/admin/**")
                .excludePathPatterns("api/admin/login/**").excludePathPatterns("api/admin/init");


//        registry.addInterceptor(adminInterceptor()).addPathPatterns("/api/Admin/**");
    }
}
