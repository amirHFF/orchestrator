package io.projectZ.orchestrator.ai.config;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/18/2026 - 1:04 AM
*/

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ProjectSpringFilter extends OncePerRequestFilter {
    public static ThreadLocal<String> currentAccessToken = new ThreadLocal<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}

