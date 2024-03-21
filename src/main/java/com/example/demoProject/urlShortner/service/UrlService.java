package com.example.demoProject.urlShortner.service;

import com.example.demoProject.urlShortner.model.SaveUrlResponse;
import com.example.demoProject.urlShortner.model.Url;
import com.example.demoProject.urlShortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;
    public static String convertUrlToBase62(String originalUrl){
        return Base62Encoder.base62Encode(originalUrl);
    }

    public SaveUrlResponse saveUrl(String originalUrl, String tinyUrl, LocalDateTime expiryTime){
        Optional<Url> existingUrl= Optional.ofNullable(urlRepository.findByOriginalUrl(originalUrl));
        if(existingUrl.isPresent()){
            Url url= existingUrl.get();
            if(url.getExpiryTime().isBefore(LocalDateTime.now())){
                url.setTinyUrl(tinyUrl);
                url.setExpiryTime(expiryTime);
                urlRepository.save(url);
                return new SaveUrlResponse("Existing URL already exists but is expired", false, "http://localhost:8080/v1/api/"+url.getTinyUrl());

            }
            return new SaveUrlResponse("Existing URL already exists", false, "http://localhost:8080/v1/api/"+url.getTinyUrl());
            }else{
            Url url= new Url();
            url.setTinyUrl(tinyUrl);
            url.setExpiryTime(expiryTime);
            url.setId(UUID.randomUUID());
            url.setOriginalUrl(originalUrl);
            url.setCreatedDate(LocalDateTime.now());
            urlRepository.save(url);
            return new SaveUrlResponse("Url added to the database.", true,"http://localhost:8080/v1/api/"+url.getTinyUrl());
        }
    }

    public Url getOriginalUrl(String tinyUrl){
       var rep= urlRepository.findByTinyUrl(tinyUrl);
       return rep;
    }
}
