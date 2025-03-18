package com.practice.urlshorten.repository;

import com.practice.urlshorten.model.ShortenedUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenerRepository extends MongoRepository<ShortenedUrl, String> {
    ShortenedUrl findShortenedUrlByShortURL(String shortUrl);
    ShortenedUrl findByURL(String url);
}
