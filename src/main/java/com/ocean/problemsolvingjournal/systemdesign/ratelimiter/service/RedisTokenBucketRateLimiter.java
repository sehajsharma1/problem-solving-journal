package com.ocean.problemsolvingjournal.systemdesign.ratelimiter.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RedisTokenBucketRateLimiter implements RateLimiterService {

    private final StringRedisTemplate redisTemplate;
    private final DefaultRedisScript<List> redisScript;

    public RedisTokenBucketRateLimiter(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisScript = new DefaultRedisScript<>();
        this.redisScript.setScriptText(LUA_SCRIPT);
        this.redisScript.setResultType(List.class);
    }

    @Override
    public boolean tryConsume(String key, double refillPerMinute, double burstCapacity, double tokensPerRequest) {
        try {
            // Keys and args to pass to Lua:
            // KEYS[1] = key
            // ARGV[1] = now (ms)
            // ARGV[2] = refillPerMinute (tokens per minute)
            // ARGV[3] = burstCapacity
            // ARGV[4] = tokensPerRequest
            String now = String.valueOf(System.currentTimeMillis());
            String rpp = String.valueOf(refillPerMinute);
            String cap = String.valueOf(burstCapacity);
            String req = String.valueOf(tokensPerRequest);

            List result = redisTemplate.execute(redisScript, Collections.singletonList("ratelimit:" + key),
                    now, rpp, cap, req);

            // result is a list: [allowed (0/1), tokensLeft (double)]
            if (result == null || result.isEmpty()) return true; // fallback allow
            Long allowed = (Long) result.get(0);
            // Double tokensLeft = Double.parseDouble(result.get(1).toString());
            return allowed == 1L;
        } catch (Exception ex) {
            // Redis down: fallback policy (choose fail-open or fail-closed). We'll fail-open here.
            // In prod you might increment a metric and implement a circuit-breaker.
            return new InMemoryRateLimiter().tryConsume(key, refillPerMinute, burstCapacity, tokensPerRequest);
        }
    }

    // Lua script (keeps single hash at key: fields tokens, last_ts)
    private static final String LUA_SCRIPT =
            "local key = KEYS[1]\n" +
                    "local now = tonumber(ARGV[1])\n" +
                    "local refill_per_minute = tonumber(ARGV[2])\n" +
                    "local capacity = tonumber(ARGV[3])\n" +
                    "local requested = tonumber(ARGV[4])\n" +
                    "\n" +
                    "-- fields: tokens, last_ts\n" +
                    "local data = redis.call('HMGET', key, 'tokens', 'last_ts')\n" +
                    "local tokens = tonumber(data[1])\n" +
                    "local last_ts = tonumber(data[2])\n" +
                    "if tokens == nil then tokens = capacity end\n" +
                    "if last_ts == nil then last_ts = now end\n" +
                    "\n" +
                    "local delta_ms = math.max(0, now - last_ts)\n" +
                    "local add = (delta_ms / 60000.0) * refill_per_minute\n" +
                    "tokens = math.min(capacity, tokens + add)\n" +
                    "\n" +
                    "local allowed = 0\n" +
                    "if tokens >= requested then\n" +
                    "  tokens = tokens - requested\n" +
                    "  allowed = 1\n" +
                    "end\n" +
                    "redis.call('HMSET', key, 'tokens', tostring(tokens), 'last_ts', tostring(now))\n" +
                    "redis.call('PEXPIRE', key, 3600000) -- expire in 1 hour to GC\n" +
                    "return {allowed, tokens}\n";
}
