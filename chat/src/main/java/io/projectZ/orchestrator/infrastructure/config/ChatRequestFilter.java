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
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ChatRequestFilter extends OncePerRequestFilter {
    public static final String ACCESS_TOKEN_STRING = "access-token";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null || token.equals("")) {
            ThreadContext.put(ACCESS_TOKEN_STRING, token);
        }

        super.doFilter(request , response, filterChain);
    }
}

