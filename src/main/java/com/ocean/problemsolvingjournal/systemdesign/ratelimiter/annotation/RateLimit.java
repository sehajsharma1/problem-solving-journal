package com.ocean.problemsolvingjournal.systemdesign.ratelimiter.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {
    // tokens per minute (refill rate)
    double refillPerMinute() default 1.0;

    // bucket capacity (max burst)
    double burstCapacity() default 5.0;

    // tokens consumed per request
    double tokensPerRequest() default 1.0;

    // key mode: API key, IP, USER_ID etc.
    String key() default ""; // optional: SpEL or literal. If empty, fallback to IP.
}
