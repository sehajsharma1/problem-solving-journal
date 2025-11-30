# 🚦 Distributed Rate Limiter for Spring Boot

This project implements a production-ready distributed and
multithread-safe Rate Limiter using:

-   Token Bucket Algorithm\
-   Redis + Lua (atomic operations)\
-   In-memory fallback\
-   Annotation-based rate limiting

## 🔧 Updated Configuration

    /**
     * Limit: 5 requests burst capacity
     * Refill Speed: 1 request per minute
     * Meaning:
     * - You can call 5 times instantly (burst)
     * - After that, only 1 request allowed per minute
     */

## 📘 Overview

This system ensures fair usage, prevents abuse, and works reliably
across multiple instances of your Spring Boot application.

### Features:

-   Burst handling (up to 5 requests instantly)
-   Distributed correctness via Redis Lua atomic script
-   Fallback in-memory limiter for Redis downtime
-   Simple `@RateLimit` annotation for usage
-   Thread-safe & scalable

## 🏗 Architecture

Client → Interceptor → RateLimiterService → Redis → Allow/Reject\
Fallback path → InMemoryRateLimiter

## 📦 Build & Run

``` bash
./mvnw clean package
java -jar target/app.jar
```

Run Redis locally:

``` bash
docker run -p 6379:6379 redis
```

## 🧪 Testing

``` bash
for i in {1..10}; do curl -i http://localhost:8080/hello; echo; done
```

You will see HTTP 429 when the limit is exceeded.

## 📄 License

MIT License.
