package com.ocean.problemsolvingjournal.systemdesign.ratelimiter.component;
import com.ocean.problemsolvingjournal.systemdesign.ratelimiter.annotation.RateLimit;
import com.ocean.problemsolvingjournal.systemdesign.ratelimiter.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiterService rateLimiterService;

    public RateLimitInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RateLimit rule = null;
        if (handler instanceof HandlerMethod handlerMethod) {
            // first method-level then class-level
            rule = handlerMethod.getMethodAnnotation(RateLimit.class);
            if (rule == null) rule = handlerMethod.getBeanType().getAnnotation(RateLimit.class);
        }
        if (rule == null) return true; // no rate limit rule -> allow

        // Extract key: prefer header "X-API-KEY", else user id, else IP
        String apiKey = request.getHeader("X-API-KEY");
        String key;
        if (apiKey != null && !apiKey.isEmpty()) {
            key = "api:" + apiKey;
        } else {
            key = "ip:" + request.getRemoteAddr();
        }

        boolean allowed = rateLimiterService.tryConsume(key, rule.refillPerMinute(), rule.burstCapacity(), rule.tokensPerRequest());
        if (!allowed) {
            response.setStatus(429);
            response.getWriter().write("Too Many Requests");
            response.setHeader("Retry-After", "1");
            return false;
        }
        return true;
    }
}
