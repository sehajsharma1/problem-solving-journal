package com.ocean.problemsolvingjournal.systemdesign.urlshortner.repository;

import com.ocean.problemsolvingjournal.systemdesign.urlshortner.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByAlias(String alias);
    Optional<UrlMapping> findByShortKey(String shortKey);
}
