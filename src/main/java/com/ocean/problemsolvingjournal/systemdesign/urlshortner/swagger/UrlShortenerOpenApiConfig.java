package com.ocean.problemsolvingjournal.systemdesign.urlshortner.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlShortenerOpenApiConfig {
    @Bean
    public GroupedOpenApi urlShortenerApi() {
        return GroupedOpenApi.builder()
                .group("URL Shortener")
                .packagesToScan("com.ocean.problemsolvingjournal.systemdesign.urlshortner")
                .build();
    }
}
