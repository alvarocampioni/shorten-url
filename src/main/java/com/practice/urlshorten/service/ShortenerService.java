package com.practice.urlshorten.service;

import com.practice.urlshorten.dto.ResponseDTO;
import com.practice.urlshorten.model.ShortenedUrl;
import com.practice.urlshorten.repository.ShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.practice.urlshorten.generator.UniqueCodeGen.generateCode;

@Service
public class ShortenerService {

    private final ShortenerRepository shortenerRepository;
    private static final int size = 6;

    @Autowired
    public ShortenerService(ShortenerRepository shortenerRepository) {
        this.shortenerRepository = shortenerRepository;
    }

    public ResponseDTO createShortenedUrl(String url) {
        if(!url.toLowerCase().startsWith("https://") && !url.toLowerCase().startsWith("http://")) {
            url = "https://" + url;
        }

        ShortenedUrl find = shortenerRepository.findByURL(url);
        if (find != null) {
            return new ResponseDTO(find.getURL(), find.getShortURL(), find.getUsed());
        }

        String code;
        String baseUrl = "http://localhost:3000/";
        do {
            code = generateCode(size);
        } while (!isCodeAvailable(baseUrl +code));

        ShortenedUrl shortenedUrl = new ShortenedUrl(url, baseUrl +code, null, null, 0);
        shortenerRepository.save(shortenedUrl);

        return new ResponseDTO(shortenedUrl.getURL(), shortenedUrl.getShortURL(), shortenedUrl.getUsed());
    }

    public String getUrlByShort(String shortUrl){
        ShortenedUrl find = shortenerRepository.findShortenedUrlByShortURL(shortUrl);
        if (find != null) {
            return find.getURL();
        } else {
            return null;
        }
    }

    public int getAccessesByShort(String shortUrl){
        ShortenedUrl find = shortenerRepository.findShortenedUrlByShortURL(shortUrl);
        if (find != null) {
            return find.getUsed();
        } else {
            return 0;
        }
    }

    public void updateUrlValues(String shortUrl){
        ShortenedUrl find = shortenerRepository.findShortenedUrlByShortURL(shortUrl);
        if(find != null){
            find.setUsed(find.getUsed() + 1);
            find.setUpdateDate(new Date());
            shortenerRepository.save(find);
        }
    }

    private boolean isCodeAvailable(String shortUrl) {
        ShortenedUrl find = shortenerRepository.findShortenedUrlByShortURL(shortUrl);
        return find == null;
    }

}
