package com.practice.urlshorten.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "shortenURL")
@Data
public class ShortenedUrl {

    @MongoId
    private String id;
    private String URL;
    private String shortURL;
    @CreatedDate
    private Date creationDate;
    private Date updateDate;
    private int used;

    public ShortenedUrl(String URL, String shortURL, Date creationDate, Date updateDate, int used) {
        this.URL = URL;
        this.shortURL = shortURL;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.used = used;
    }

}
