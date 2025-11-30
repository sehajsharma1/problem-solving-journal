package com.ocean.problemsolvingjournal.systemdesign.ratelimiter.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterOpenApiConfig {
    @Bean
    public GroupedOpenApi rateLimiterApi() {
        return GroupedOpenApi.builder()
                .group("Rate Limiter")
                .packagesToScan("com.ocean.problemsolvingjournal.systemdesign.ratelimiter")
                .build();
    }
}

