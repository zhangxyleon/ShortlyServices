package com.internProject.shortly.service;

import com.internProject.shortly.entity.Url;

public interface UrlService {
    public String createShortUrl(String longUrl, String encodeMethod);
    public String getByShortUrl(String ShortUrl);
}
