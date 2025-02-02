package com.practice.urlshorten.controller;

import com.practice.urlshorten.service.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
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

    @GetMapping(value = "/{pathCode}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> redirect(@PathVariable String pathCode) {
        shortenerService.updateUrlValues(pathCode);
        String url = shortenerService.getUrlByCode(pathCode);
        if(url == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", url).build();
    }
}
