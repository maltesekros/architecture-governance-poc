package com.chrismicallef.poc.parentservice.management.config;

import com.chrismicallef.poc.parentservice.management.rest.RestTemplateHeaderModifierInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestClientConfig {

    @Value("${service.type}")
    private String serviceType;

    @Value("${service.name}")
    private String serviceName;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors
                = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestTemplateHeaderModifierInterceptor(serviceType, serviceName));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}

