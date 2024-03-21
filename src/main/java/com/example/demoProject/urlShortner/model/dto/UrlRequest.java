package com.example.demoProject.urlShortner.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UrlRequest {
    private String originalUrl;
    private Long expiryTimeInHours;
    private Long expiryTimeInDays;
}
