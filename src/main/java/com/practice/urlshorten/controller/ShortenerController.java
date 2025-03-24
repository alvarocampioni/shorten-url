package com.practice.urlshorten.controller;

import com.practice.urlshorten.dto.ResponseDTO;
import com.practice.urlshorten.service.ShortenerService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create Short URL", description = "Returns the short URL.")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createShortenedUrl(@RequestParam String url){
        return shortenerService.createShortenedUrl(url);
    }

    //get original url using short-url code
    @GetMapping
    @Operation(summary = "Obtain Original URL", description = "Returns the original URL using the short URL.")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String getUrlByShort(@RequestParam String shortUrl){
        return shortenerService.getUrlByShort(shortUrl);
    }

    //get the number of times the link was used
    @GetMapping("/stats")
    @Operation(summary = "URL Stats", description = "Obtains the number of uses the short URL received.")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public int getAccessesByShort(@RequestParam String shortUrl){
        return shortenerService.getAccessesByShort(shortUrl);
    }
}
