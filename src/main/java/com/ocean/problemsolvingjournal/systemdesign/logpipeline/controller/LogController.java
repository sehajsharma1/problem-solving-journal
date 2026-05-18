package com.ocean.problemsolvingjournal.systemdesign.logpipeline.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;


@RestController
@RequestMapping("/api/v1/log/pipeline")
public class LogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/test")
    public String test() {
        log.info("test executed at {}", Instant.now());
        try {
            throw new IllegalStateException("illegal state");
        } catch (Exception e) {
            log.error("exception in test:{}", e.getMessage());
        }
        return "OK";
    }
}
