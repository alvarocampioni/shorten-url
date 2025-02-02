package com.practice.urlshorten.repository;

import com.practice.urlshorten.model.Shortener;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenerRepository extends MongoRepository<Shortener, String> {
    Shortener findByShortCode(String shortCode);
    Shortener findByURL(String url);
}
