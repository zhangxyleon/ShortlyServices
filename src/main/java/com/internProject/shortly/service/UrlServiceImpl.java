package com.internProject.shortly.service;

import com.internProject.shortly.DAO.UrlRepository;
import com.internProject.shortly.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlServiceImpl implements  UrlService{

    @Autowired
    private UrlRepository urlRepository;

    @Override
    @Transactional
    public void createUrl(Url url) {
        urlRepository.save(url);
    }

    @Override
    public Url getByShortUrl(String shortUrl) {
        Url url = urlRepository.getByShortUrl(shortUrl);

        return url;
    }
}
