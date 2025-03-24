package com.practice.urlshorten.controller;

import com.practice.urlshorten.service.ShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RedirectController {

    private final ShortenerService shortenerService;

    @Autowired
    public RedirectController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    //redirects to the original url (browser)
    @GetMapping(value = "/{shortCode}")
    @Operation(summary = "Redirect URL", description = "Redirects based on the short URL path code, paste the short URL on a browser to effectively redirect to the original URL.")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> redirect(@PathVariable String shortCode) {
        String baseUrl = "http://localhost:3000/";

        shortenerService.updateUrlValues(baseUrl + shortCode);
        String url = shortenerService.getUrlByShort(baseUrl + shortCode);
        if(url == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", url).build();
    }
}
