package com.practice.urlshorten.controller;

import com.practice.urlshorten.dto.ResponseDTO;
import com.practice.urlshorten.service.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shorten")
public class ShortenerController {

    private final ShortenerService shortenerService;

    @Autowired
    public ShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    //create short-url
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createShortenedUrl(@RequestParam String url){
        return shortenerService.createShortenedUrl(url);
    }

    //get original url using short-url code
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String getUrlByShort(@RequestParam String shortUrl){
        return shortenerService.getUrlByShort(shortUrl);
    }

    //get the number of times the link was used
    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public int getAccessesByShort(@RequestParam String shortUrl){
        return shortenerService.getAccessesByShort(shortUrl);
    }
}
