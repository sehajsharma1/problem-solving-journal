package com.ocean.problemsolvingjournal.systemdesign.urlshortner.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 /**
  * Configuration class that creates a DataSource bean backed by HikariCP.
  *
  * Purpose:
  * - Convert Spring Boot's DataSourceProperties into a HikariDataSource instance.
  * - Apply a few minimal, safe defaults only when those values are not already
  *   provided via application configuration (e.g., application.yml / properties).
  *
  * Key points:
  * - @ConditionalOnMissingBean: only creates this DataSource if no other DataSource
  *   bean is present in the context (prevents overriding user-provided beans).
  * - DataSourceProperties.initializeDataSourceBuilder(): builds the DataSource using
  *   properties (URL, username, password, driver, and any hikari.* prefixed props).
  * - The code sets defaults for pool name, maximum pool size, and minimum idle
  *   threads when those values are not explicitly set.
  */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource(DataSourceProperties properties) {
        // Build a HikariDataSource using Spring Boot's DataSourceProperties.
        // This will apply any DataSource-related properties from application.yml
        // (including hikari.* prefixed properties).
        HikariDataSource ds = properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();

        // Apply minimal safe defaults only if not provided via application.yml
        // - Pool name: useful for metrics/logging. Set a recognizable default.
        if (ds.getPoolName() == null || ds.getPoolName().isEmpty()) {
            ds.setPoolName("HikariPool-URLShortener");
        }
        // - Maximum pool size: avoid unbounded default of 0; choose a conservative default.
        if (ds.getMaximumPoolSize() == 0) {
            ds.setMaximumPoolSize(10);
        }
        // - Minimum idle: ensure a couple of ready connections to reduce cold-start latency.
        if (ds.getMinimumIdle() == 0) {
            ds.setMinimumIdle(2);
        }

        return ds;
    }
}
