package com.chrismicallef.poc.parentservice.management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    @Value("${service.type}")
    private String serviceType;

    @Value("${service.name}")
    private String serviceName;

    @PostConstruct
    private void showInfo() {
        LOG.info("*********************************************");
        LOG.info(String.format("Service Type:[%s]", serviceType));
        LOG.info(String.format("Service Name: [%s]", serviceName));
        LOG.info("*********************************************");
    }
}
