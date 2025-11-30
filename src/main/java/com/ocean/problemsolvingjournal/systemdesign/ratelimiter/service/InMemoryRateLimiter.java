package com.ocean.problemsolvingjournal.systemdesign.ratelimiter.service;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRateLimiter implements RateLimiterService {

    private static class Bucket {
        volatile double tokens;
        volatile long lastRefillTs;
        final double capacity;
        final double refillPerMinute;

        Bucket(double tokens, long ts, double capacity, double refillPerMinute) {
            this.tokens = tokens;
            this.lastRefillTs = ts;
            this.capacity = capacity;
            this.refillPerMinute = refillPerMinute;
        }
    }

    private final ConcurrentHashMap<String, Bucket> map = new ConcurrentHashMap<>();

    @Override
    public boolean tryConsume(String key, double refillPerMinute, double burstCapacity, double tokensPerRequest) {
        long now = System.currentTimeMillis();
        Bucket b = map.compute(key, (k, existing) -> {
            if (existing == null) {
                return new Bucket(burstCapacity, now, burstCapacity, refillPerMinute);
            } else {
                // refill
                long delta = now - existing.lastRefillTs;
                double add = (delta / 60000.0) * existing.refillPerMinute;
                existing.tokens = Math.min(existing.capacity, existing.tokens + add);
                existing.lastRefillTs = now;
                return existing;
            }
        });

        synchronized (b) {
            if (b.tokens >= tokensPerRequest) {
                b.tokens -= tokensPerRequest;
                return true;
            } else {
                return false;
            }
        }
    }
}
