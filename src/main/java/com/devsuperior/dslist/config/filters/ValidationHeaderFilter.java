package com.devsuperior.dslist.config.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Slf4j
public class ValidationHeaderFilter implements Filter {

    private static final String KEY_VALUE_FLOW_ID = "flowId";

    private static final String KEY_VALUE_CORRELATION_ID = "correlationId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("Logging Request Resource {}: {}", request.getMethod(), request.getRequestURI());

        if (isInvalidKeyHeaderValue(request, response, KEY_VALUE_FLOW_ID)) return;
        if (isInvalidKeyHeaderValue(request, response, KEY_VALUE_CORRELATION_ID)) return;

        filterChain.doFilter(servletRequest, servletResponse);
        log.info("Logging Response: {} | {}", response.getContentType(), response.getStatus());
    }


    private boolean isInvalidKeyHeaderValue(HttpServletRequest request, HttpServletResponse response, String key) {

        if (StringUtils.isEmpty(getValueRequestHeader(request, key)) || StringUtils.isBlank(getValueRequestHeader(request, key))) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.info("Logging Response Error: {} | {}", HttpStatus.valueOf(HttpServletResponse.SC_UNAUTHORIZED), "Missing: " + key);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    private String getValueRequestHeader(HttpServletRequest request,
                                         final String key) {
        return request.getHeader(key);
    }

}
