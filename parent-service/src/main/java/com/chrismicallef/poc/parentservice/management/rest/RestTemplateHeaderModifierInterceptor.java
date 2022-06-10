package com.chrismicallef.poc.parentservice.management.rest;

import com.chrismicallef.poc.parentservice.management.config.CustomHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    private final String serviceType;
    private final String serviceName;

    public RestTemplateHeaderModifierInterceptor(final String serviceType, final String serviceName) {
        this.serviceType = serviceType;
        this.serviceName = serviceName;
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {
        // Add custom headers to all requests
        request.getHeaders().add(CustomHeaders.SERVICE_TYPE.getValue(), this.serviceType);
        request.getHeaders().add(CustomHeaders.SERVICE_NAME.getValue(), this.serviceName);
        ClientHttpResponse response = execution.execute(request, body);
        return response;
    }
}
