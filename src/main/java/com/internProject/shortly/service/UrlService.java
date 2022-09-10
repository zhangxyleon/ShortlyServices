package com.internProject.shortly.service;


public interface UrlService {
    public String createShortUrl(String longUrl, String encodeMethod);
    public String getByShortUrl(String ShortUrl);

}
