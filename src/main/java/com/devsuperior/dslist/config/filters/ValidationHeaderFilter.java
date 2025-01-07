package com.devsuperior.dslist.config.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class ValidationHeaderFilter implements Filter {

    private static final String KEY_VALUE_FLOW_ID = "flowId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletRequest;

        final var flowId = request.getHeader(KEY_VALUE_FLOW_ID);

        if (StringUtils.isAllEmpty(flowId) || StringUtils.isAllBlank(flowId)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
