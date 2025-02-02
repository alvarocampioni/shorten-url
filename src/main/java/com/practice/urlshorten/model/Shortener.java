package com.practice.urlshorten.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "shortenURL")
@Setter
@Getter
@NoArgsConstructor
@Data
public class Shortener {

    @MongoId
    private String id;
    private String URL;
    private String shortCode;
    private Date creationDate;
    private Date updateDate;
    private int used;

    public Shortener(String URL, String shortCode, Date creationDate, Date updateDate, int used) {
        this.URL = URL;
        this.shortCode = shortCode;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.used = used;
    }

}
