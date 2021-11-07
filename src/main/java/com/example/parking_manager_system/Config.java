package com.example.parking_manager_system;

import com.example.parking_manager_system.Interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

//        configurer.addPathPrefix("api", c -> true);
    }

    @Autowired
    UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/login/**").excludePathPatterns("/user/register/**");


//        registry.addInterceptor(adminInterceptor()).addPathPatterns("/api/Admin/**");
    }
}
