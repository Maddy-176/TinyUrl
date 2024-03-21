package com.example.demoProject.urlShortner.controller;

import com.example.demoProject.urlShortner.model.SaveUrlResponse;
import com.example.demoProject.urlShortner.model.dto.UrlRequest;
import com.example.demoProject.urlShortner.model.Url;
import com.example.demoProject.urlShortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/api")
public class UrlController {

    @Autowired
    private UrlService urlService;
    @PostMapping("/")
    public ResponseEntity<SaveUrlResponse> addUrl(@RequestBody UrlRequest urlRequest){
        LocalDateTime expiryTime=null;
        if(urlRequest.getExpiryTimeInHours()!=null){
            expiryTime=LocalDateTime.now().plusHours(urlRequest.getExpiryTimeInHours());
        }else if(urlRequest.getExpiryTimeInDays() !=null){
            expiryTime=LocalDateTime.now().plusDays(urlRequest.getExpiryTimeInDays());
        }


        String tinyUrl= UrlService.convertUrlToBase62(urlRequest.getOriginalUrl());
       return  ResponseEntity.ok(urlService.saveUrl(urlRequest.getOriginalUrl(),tinyUrl,expiryTime));

    }

    @GetMapping("/{url}")
    public ResponseEntity<String> getTinyUrl(@PathVariable(required = true) String url){
       Url originalUrl= urlService.getOriginalUrl(url);
       System.out.println("originalUr."+" "+originalUrl);
       if(originalUrl!=null){
           String fullUrl = "https://" + originalUrl.getOriginalUrl();
           return ResponseEntity.status(302).location(URI.create(fullUrl)).build();
       }
        return ResponseEntity.notFound().build();
       
    }
}
