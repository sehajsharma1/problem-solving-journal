package com.ocean.problemsolvingjournal.systemdesign.urlshortner.controller;

import com.ocean.problemsolvingjournal.systemdesign.urlshortner.dto.CreateShortUrlRequest;
import com.ocean.problemsolvingjournal.systemdesign.urlshortner.dto.CreateShortUrlResponse;
import com.ocean.problemsolvingjournal.systemdesign.urlshortner.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {
    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("urlshortner/api/v1/shorten")
    public ResponseEntity<?> shorten(@RequestBody CreateShortUrlRequest req) {
        String key = service.createShortKey(req);

        String inputDomain = req.getDomain();
        String domainForResponse;
        String shortUrl;

        if (inputDomain == null || inputDomain.isBlank()) {
            domainForResponse = "short.ly";
            shortUrl = "https://" + domainForResponse + "/" + key;
        } else if (inputDomain.startsWith("http://") || inputDomain.startsWith("https://")) {
            // use provided scheme, avoid double trailing slash
            String base = inputDomain.endsWith("/") ? inputDomain.substring(0, inputDomain.length() - 1) : inputDomain;
            shortUrl = base + "/" + key;
            domainForResponse = inputDomain;
        } else {
            // no scheme provided -> default to https
            domainForResponse = inputDomain;
            shortUrl = "https://" + inputDomain + "/" + key;
        }

        return ResponseEntity.ok(
                new CreateShortUrlResponse(shortUrl, key, domainForResponse, req.getExpires_at())
        );
    }

    @GetMapping("/{key}")
    public ResponseEntity<?> redirect(@PathVariable String key) {
        return service.getLongUrl(key)
                .map(url -> ResponseEntity.status(302).location(URI.create(url)).build())
                .orElse(ResponseEntity.notFound().build());
    }
}

