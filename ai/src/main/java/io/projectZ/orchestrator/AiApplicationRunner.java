package io.projectZ.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "io.projectZ.orchestrator")
public class AiApplicationRunner extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AiApplicationRunner.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AiApplicationRunner.class, args);
    }
}