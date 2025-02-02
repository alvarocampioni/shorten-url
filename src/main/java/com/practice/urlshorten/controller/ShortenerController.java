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

    @Value("${server.address}")
    private String baseUrl;
    @Value("${server.port}")
    private int basePort;

    @Autowired
    public ShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createShortenedUrl(@RequestBody String url){
        return baseUrl + ":" + basePort + "/" + shortenerService.createShortenedUrl(url);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String getUrlByCode(@RequestParam String pathCode){
        return shortenerService.getUrlByCode(pathCode);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public int getAccessesByCode(@RequestParam String pathCode){
        return shortenerService.getAccessesByCode(pathCode);
    }
}
