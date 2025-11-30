package com.ocean.problemsolvingjournal.systemdesign.ratelimiter.service;

public interface RateLimiterService {
    boolean tryConsume(String key, double permitsPerSecond, double burstCapacity, double tokensPerRequest);
}
