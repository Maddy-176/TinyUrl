package com.example.demoProject.urlShortner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveUrlResponse {
    private String message;
    private boolean isWarning;
    private String tinyUrl;
}
