package com.ocean.problemsolvingjournal.systemdesign.urlshortner.dto;

public class CreateShortUrlRequest {
    private String url;
    private String domain;
    private String alias;
    private String tags;
    private String expires_at;
    private String description;

    // getters and setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
    public String getExpires_at() { return expires_at; }
    public void setExpires_at(String expires_at) { this.expires_at = expires_at; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
