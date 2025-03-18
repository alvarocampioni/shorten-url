package com.practice.urlshorten.dto;

public record ResponseDTO(String longUrl, String shortUrl, int used) {
}
