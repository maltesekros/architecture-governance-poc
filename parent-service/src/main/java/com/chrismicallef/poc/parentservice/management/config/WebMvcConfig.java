package com.chrismicallef.poc.parentservice.management.config;

import com.chrismicallef.poc.parentservice.management.rest.RestCallInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${service.type}")
    private String serviceType;

    @Value("${service.name}")
    private String serviceName;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // LogInterceptor apply to all URLs.
        registry.addInterceptor(new RestCallInterceptor(serviceType, serviceName));
    }
}
