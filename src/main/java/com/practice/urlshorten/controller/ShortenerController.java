package com.practice.urlshorten.controller;

import com.practice.urlshorten.service.ShortenerService;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public String createShortenedUrl(@RequestBody String url){
        return shortenerService.createShortenedUrl(url);
    }

    //get original url using short-url code
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String getUrlByCode(@RequestParam String pathCode){
        return shortenerService.getUrlByCode(pathCode);
    }

    //get the number of times the link was used
    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public int getAccessesByCode(@RequestParam String pathCode){
        return shortenerService.getAccessesByCode(pathCode);
    }
}
