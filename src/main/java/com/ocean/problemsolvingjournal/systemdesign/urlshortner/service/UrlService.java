package com.ocean.problemsolvingjournal.systemdesign.urlshortner.service;


import com.ocean.problemsolvingjournal.systemdesign.urlshortner.dto.CreateShortUrlRequest;
import com.ocean.problemsolvingjournal.systemdesign.urlshortner.model.UrlMapping;
import com.ocean.problemsolvingjournal.systemdesign.urlshortner.repository.UrlMappingRepository;
import com.ocean.problemsolvingjournal.systemdesign.urlshortner.util.Base62;
import com.ocean.problemsolvingjournal.systemdesign.urlshortner.util.IdGenerator;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UrlService {
    private final UrlMappingRepository repo;
    private final StringRedisTemplate redis;
    private final IdGenerator idGen = new IdGenerator(1, 1);

    public UrlService(UrlMappingRepository repo, StringRedisTemplate redis) {
        this.repo = repo;
        this.redis = redis;
    }

    @Transactional
    public String createShortKey(CreateShortUrlRequest req) {
        if (req.getUrl() == null || !req.getUrl().startsWith("http"))
            throw new IllegalArgumentException("Invalid URL");

        String alias = req.getAlias();
        if (alias != null && repo.findByAlias(alias).isPresent())
            throw new IllegalArgumentException("Alias already exists");

        UrlMapping mapping = new UrlMapping();
        mapping.setLongUrl(req.getUrl());
        mapping.setDomain(req.getDomain());
        mapping.setAlias(alias);
        mapping.setTags(req.getTags());
        mapping.setDescription(req.getDescription());

        if (req.getExpires_at() != null) {
            LocalDateTime ldt = LocalDateTime.parse(req.getExpires_at(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Instant instant = ldt.atZone(ZoneId.of("UTC")).toInstant();
            mapping.setExpiresAt(instant);
        }
        long id = idGen.nextId();
        String shortKey = alias != null ? alias : Base62.encode(id);
        mapping.setShortKey(shortKey);
        repo.save(mapping);

        // cache immediately
        long ttl = mapping.getExpiresAt() != null ?
                mapping.getExpiresAt().getEpochSecond() - Instant.now().getEpochSecond() : 0;

        if (ttl > 0)
            redis.opsForValue().set(shortKey, req.getUrl(), ttl, TimeUnit.SECONDS);
        else
            redis.opsForValue().set(shortKey, req.getUrl());

        return shortKey;
    }

    public Optional<String> getLongUrl(String key) {
        String cached = redis.opsForValue().get(key);
        if (cached != null) return Optional.of(cached);

        return repo.findByAlias(key)
                .map(UrlMapping::getLongUrl)
                .or(() -> repo.findByShortKey(key).map(UrlMapping::getLongUrl))
                .map(url -> {
                    redis.opsForValue().set(key, url, 1, TimeUnit.HOURS); // cache hot links
                    return url;
                });
    }
}
