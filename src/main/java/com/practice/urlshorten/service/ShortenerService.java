package com.practice.urlshorten.service;

import com.practice.urlshorten.model.Shortener;
import com.practice.urlshorten.repository.ShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
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

    public String createShortenedUrl(String url) {
        if(!url.toLowerCase().startsWith("https://") && !url.startsWith("http://")) {
            url = "https://" + url;
        }

        Shortener find = shortenerRepository.findByURL(url);
        if (find != null) {
            return find.getShortCode();
        }

        String code;
        do {
            code = generateCode(size);
        } while (!isCodeAvailable(code));
        Shortener shortener = new Shortener(url, code, new Date(), new Date(), 0);
        shortenerRepository.save(shortener);
        return code;
    }

    public String getUrlByCode(String code){
        Shortener find = shortenerRepository.findByShortCode(code);
        if (find != null) {
            return find.getURL();
        } else {
            return null;
        }
    }

    public int getAccessesByCode(String code){
        Shortener find = shortenerRepository.findByShortCode(code);
        if (find != null) {
            return find.getUsed();
        } else {
            return 0;
        }
    }

    public void updateUrlValues(String code){
        Shortener find = shortenerRepository.findByShortCode(code);
        if(find != null){
            find.setUsed(find.getUsed() + 1);
            find.setUpdateDate(new Date());
            shortenerRepository.save(find);
        }
    }

    private boolean isCodeAvailable(String code) {
        Shortener find = shortenerRepository.findByShortCode(code);
        return find == null;
    }

}
