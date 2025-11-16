package com.ocean.problemsolvingjournal.systemdesign.urlshortner.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("URL Shortener API")
                .version("v1")
                .description("API for creating and resolving shortened URLs.")
            );
    }

    @Bean
    public GroupedOpenApi urlShortenerApi() {
        return GroupedOpenApi.builder()
                .group("url-shortener")
                .packagesToScan("com.ocean.problemsolvingjournal.systemdesign.urlshortner")
                .build();
    }
}
