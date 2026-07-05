package io.projectZ.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "io.projectZ.orchestrator*")
public class applicationRunner extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(applicationRunner.class , args);
    }
}