package com.practice.urlshorten.config;

import com.practice.urlshorten.model.ShortenedUrl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;

@Configuration
@EnableMongoAuditing
public class MongoConfig {

    // setting url time-to-live - 30min
    @Bean
    public CommandLineRunner createTTLIndex(MongoTemplate mongoTemplate) {

        return ags -> {
            IndexOperations indexOperations = mongoTemplate.indexOps(ShortenedUrl.class);
            boolean exists = indexOperations.getIndexInfo().stream().anyMatch(indexInfo -> indexInfo.getName().equals("creationDate_1"));

            if(!exists) {
                IndexOperations indexOps = mongoTemplate.indexOps(ShortenedUrl.class);
                indexOps.ensureIndex(new Index().on("creationDate", org.springframework.data.domain.Sort.Direction.ASC)
                        .expire(1800));
            }
        };
    }
}
