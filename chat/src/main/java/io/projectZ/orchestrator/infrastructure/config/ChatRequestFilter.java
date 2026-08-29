package io.projectZ.orchestrator.infrastructure.config;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/18/2026 - 2:42 PM
*/

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

public class ChatRequestFilter extends OncePerRequestFilter {
    public static final String ACCESS_TOKEN_STRING = "access-token";
    public static final String REFERENCE_NUMBER_STRING = "referenceNumber";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String referenceNumber = request.getHeader("REFERENCE_NUMBER_STRING");
        if (referenceNumber != null) {
            MDC.put(REFERENCE_NUMBER_STRING, referenceNumber);
            ThreadContext.put(REFERENCE_NUMBER_STRING, referenceNumber);
        } else {
            referenceNumber = UUID.randomUUID().toString();
            MDC.put(REFERENCE_NUMBER_STRING, referenceNumber);
            ThreadContext.put(REFERENCE_NUMBER_STRING, referenceNumber);

        }
        MDC.put("referenceNumber", "");
        String token = request.getHeader("Authorization");
        if (token != null || token.equals("")) {
            ThreadContext.put(ACCESS_TOKEN_STRING, token);
        }

        super.doFilter(request, response, filterChain);
    }
}

