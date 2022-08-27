package com.internProject.shortly.service;

import com.internProject.shortly.DAO.UrlRepository;
import com.internProject.shortly.entity.Url;
import com.internProject.shortly.exception.UrlRequestException;
import com.internProject.shortly.util.UrlShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.internProject.shortly.util.UrlValidator.isUrlValid;


@Service
public class UrlServiceImpl implements  UrlService{

    @Autowired
    private UrlRepository urlRepository;

    @Override
    @Transactional
    public String createShortUrl(String longUrl, String encodeMethod) {
        //check whether url is valid
        if (!isUrlValid(longUrl)){
            throw new UrlRequestException(HttpStatus.BAD_REQUEST,"Unable to shorter that link. It is not a valid Url");
        }

        //check whether encoding method is valid
        if (!encodeMethod.equals("random") && (!encodeMethod.equals("base62")) ) {
            throw new UrlRequestException(HttpStatus.BAD_REQUEST,"Unable to shorter that link. It is not a valid encoding method");

        }

        //generated a 6-letters code
        String generatedCode;
        generatedCode = UrlShortener.encodeUrl(encodeMethod);

        //check whether this new short url is already used
        while (urlRepository.getByShortUrl("http://localhost:8080/"+ generatedCode)!=null){
            generatedCode = UrlShortener.encodeUrl(encodeMethod);
        }

        // save the url Mapping
        String shortUrl= "http://localhost:8080/" + generatedCode;
        Url urlMapping = new Url();
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setLongUrl(longUrl);
        urlRepository.save(urlMapping);

        return shortUrl;
    }

    @Override
    @Transactional
    @Cacheable(value="Url", key="#shortUrl")
    public String getByShortUrl(String shortUrl) {


        Url urlMapping = urlRepository.getByShortUrl(shortUrl);

        //check whether this short Url is in the database
        if (urlMapping==null){
            throw new UrlRequestException(HttpStatus.NOT_FOUND,"Unable to get original link. It is not a valid  shorter Url");
        }

        return urlMapping.getLongUrl();
    }
}
