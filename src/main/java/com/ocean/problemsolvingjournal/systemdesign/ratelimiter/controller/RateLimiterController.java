package com.ocean.problemsolvingjournal.systemdesign.ratelimiter.controller;

import com.ocean.problemsolvingjournal.systemdesign.ratelimiter.annotation.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiterController {

    /**
     * Limit: 5 requests burst capacity
     * Refill Speed: 1 request per minute
     * Meaning:
     * - You can call 5 times instantly (burst)
     * - After that, only 1 request allowed per minute
     */
    @RateLimit(
            refillPerMinute = 1.0,   // refill rate per minute
            burstCapacity = 5.0,      // max burst capacity
            tokensPerRequest = 1.0
    )
    @GetMapping("/api/test")
    public String testRateLimit(@RequestHeader("X-API-KEY") String key) {
        return "Request Accepted 👉 " + System.currentTimeMillis();
    }
}
