package io.projectZ.orchestrator.ai.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

	private final static String PHOENIX_ADDRESS = "https://130.185.121.173/";

	@Bean
	public OpenAPI getOpenApi() {
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement()
						.addList("Bearer Authentication"))
				.components(new Components()
						.addSecuritySchemes("Bearer Authentication", new SecurityScheme()
								.scheme("bearer")
								.bearerFormat("JWT")
								.in(SecurityScheme.In.HEADER)
								.type(SecurityScheme.Type.APIKEY)
								.name("Authorization")))
				.info(new Info()
						.title("PHOENIX Api ")
						.version("0.0.1"))
				.servers(Collections.singletonList(new Server().url("http://localhost:8091")));
	}
}
