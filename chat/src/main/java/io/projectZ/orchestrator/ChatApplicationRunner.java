package io.projectZ.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "io.projectZ.orchestrator*")
public class ChatApplicationRunner extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ChatApplicationRunner.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ChatApplicationRunner.class, args);
    }
}