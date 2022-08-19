package com.internProject.shortly.service;

import com.internProject.shortly.entity.Url;

public interface UrlService {
    public void createUrl(Url url);
    public Url getByShortUrl(String ShortUrl);
}
