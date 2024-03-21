package com.example.demoProject.urlShortner.repository;

import com.example.demoProject.urlShortner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UrlRepository extends JpaRepository<Url, UUID> {
    Url findByTinyUrl(String tinyUrl);
    Url findByOriginalUrl(String originalUrl);
}
