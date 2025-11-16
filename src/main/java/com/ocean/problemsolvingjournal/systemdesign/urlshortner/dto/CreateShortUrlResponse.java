package com.ocean.problemsolvingjournal.systemdesign.urlshortner.dto;

public class CreateShortUrlResponse {
    private String short_url;
    private String key;
    private String domain;
    private String expires_at;

    public CreateShortUrlResponse(String short_url, String key, String domain, String expires_at) {
        this.short_url = short_url;
        this.key = key;
        this.domain = domain;
        this.expires_at = expires_at;
    }

    public String getShort_url() { return short_url; }
    public String getKey() { return key; }
    public String getDomain() { return domain; }
    public String getExpires_at() { return expires_at; }
}

