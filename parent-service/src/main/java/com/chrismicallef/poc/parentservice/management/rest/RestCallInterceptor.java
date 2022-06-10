package com.chrismicallef.poc.parentservice.management.rest;

import com.chrismicallef.poc.parentservice.management.config.CustomHeaders;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class RestCallInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(RestCallInterceptor.class);
    private static final String NO_CLIENT_NAME = "<NO_NAME>";
    
    private final String serviceType;
    private final String serviceName;
    private final ConcurrentHashMap<String, Counter> callCounters;

    public RestCallInterceptor(final String serviceType, final String serviceName) {
        this.serviceType = serviceType;
        this.serviceName = serviceName;
        this.callCounters = new ConcurrentHashMap<>();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String callerServiceName = request.getHeader(CustomHeaders.SERVICE_NAME.getValue());
        String callerServiceType = request.getHeader(CustomHeaders.SERVICE_TYPE.getValue());
        logRequest(request, callerServiceName, callerServiceType);
        // Swap nulls to a default name
        callerServiceName = callerServiceName == null ? NO_CLIENT_NAME : callerServiceName;
        // Increment counter calls of clients
        incCounter(callerServiceName, false);
        String flow = callerServiceType + "->" + this.serviceType;
        // Increment counter calls of service type (client) -> service type (server)
        incCounter(flow, true);
        return true;
    }

    private void logRequest(HttpServletRequest request, String callerServiceName, String callerServiceType) {
        LOG.debug(String.format("[preHandle][%s][%s][%s][%s]", request, request.getMethod(), request.getRequestURI(), getParameters(request)));
        LOG.debug(String.format("[caller] service-name: [%s] service-type: [%s]", callerServiceName, callerServiceType));
        LOG.debug(String.format("[receiver] service-name: [%s] [service-type: [%s]", this.serviceName, this.serviceType));
    }

    private void incCounter(String counterId, boolean flow) {
        Counter counter = callCounters.get(counterId);
        if (callCounters.get(counterId) == null) {
            String prefix = flow ? "flow." : "client.";
            counter = Metrics.globalRegistry.counter(prefix + counterId);
            counter.increment();
            callCounters.put(counterId, counter);
        }
        else {
            counter.increment();
        }
    }

    private String getParameters(HttpServletRequest request) {
        StringBuffer posted = new StringBuffer();
        Enumeration<?> e = request.getParameterNames();
        if (e != null) {
            posted.append("?");
        }
        while (e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            }
            String curr = (String) e.nextElement();
            posted.append(curr + "=");
            if (curr.contains("password")
                    || curr.contains("pass")
                    || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }
        String ip = request.getHeader("X-FORWARDED-FOR");
        String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
        if (ipAddr!=null && !ipAddr.equals("")) {
            posted.append("&_psip=" + ipAddr);
        }
        return posted.toString();
    }

    private String getRemoteAddr(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            LOG.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }
}
