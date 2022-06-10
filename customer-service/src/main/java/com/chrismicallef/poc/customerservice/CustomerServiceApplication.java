package com.chrismicallef.poc.customerservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.chrismicallef.poc"})
public class CustomerServiceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceApplication.class);

	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(CustomerServiceApplication.class, args);
		// Show all bean names
		for (String beanName : applicationContext.getBeanDefinitionNames()) {
			LOG.debug(beanName);
		}
	}

}

