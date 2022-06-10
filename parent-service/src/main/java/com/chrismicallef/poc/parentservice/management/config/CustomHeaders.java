package com.chrismicallef.poc.parentservice.management.config;

public enum CustomHeaders {

    SERVICE_NAME("service-name"),
    SERVICE_TYPE("service-type");

    private String value;

    CustomHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
