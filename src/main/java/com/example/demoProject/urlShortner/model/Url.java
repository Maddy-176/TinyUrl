package com.example.demoProject.urlShortner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String originalUrl;
    @Column(unique = true)
    private String tinyUrl;

    private LocalDateTime createdDate;

    private LocalDateTime expiryTime;

}
