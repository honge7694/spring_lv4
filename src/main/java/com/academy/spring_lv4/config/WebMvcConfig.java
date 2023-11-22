package com.academy.spring_lv4.config;


import com.academy.spring_lv4.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authenticationInterceptor; // 구현한 인터셉터

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/teachers/**"); // 인터셉터 적용할 URL
                //.excludePathPatterns(""); // 인터셉터 적용하지 않을 URL
    }

}
