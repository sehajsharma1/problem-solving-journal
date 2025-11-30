package com.ocean.problemsolvingjournal.systemdesign.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalSwaggerConfig {

    @Bean
    public OpenAPI appOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("System Design Sandbox APIs")
                        .version("v1")
                        .description("A consolidated API documentation for all modules (URL Shortener, Rate Limiter, etc.)")
                );
    }
}

