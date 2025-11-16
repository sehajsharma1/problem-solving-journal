package com.ocean.problemsolvingjournal.systemdesign.urlshortner.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "url_mapping", indexes = {
        @Index(columnList = "alias", unique = true)
})
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // internal DB ID

    @Column(nullable = false, length = 2048)
    private String longUrl;

    private String shortKey;  // generated Base62 key
    private String domain;
    private String alias;
    private String tags;
    private String description;
    private Instant expiresAt;
    private Instant createdAt = Instant.now();

    // getters and setters
    public Long getId() { return id; }
    public String getLongUrl() { return longUrl; }
    public void setLongUrl(String longUrl) { this.longUrl = longUrl; }
    public String getShortKey() { return shortKey; }
    public void setShortKey(String shortKey) { this.shortKey = shortKey; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    public Instant getCreatedAt() { return createdAt; }
}
