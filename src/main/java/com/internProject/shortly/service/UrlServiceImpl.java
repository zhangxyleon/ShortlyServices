package com.internProject.shortly.service;

import com.internProject.shortly.DAO.UrlBase62Repository;
import com.internProject.shortly.DAO.UrlRepository;
import com.internProject.shortly.entity.Url;
import com.internProject.shortly.entity.UrlBase62;
import com.internProject.shortly.exception.UrlRequestException;
import com.internProject.shortly.redis.RedisCacheService;
import com.internProject.shortly.redis.RedisSequenceService;
import com.internProject.shortly.util.UrlShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.internProject.shortly.util.UrlValidator.isUrlValid;


@Service
public class UrlServiceImpl implements  UrlService{

    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private UrlBase62Repository urlBase62Repository;
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private RedisSequenceService redisSequenceService;


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
        String shortUrl = "";

        if (encodeMethod.equals("random")){
            //generated a 6-letters code
            String generatedCode;
            generatedCode = UrlShortener.encodeRandom(7);
            System.out.println(generatedCode);

            //check whether this new short url is already used
            while (urlRepository.getByShortUrl( generatedCode)!=null){
                generatedCode = UrlShortener.encodeUrl(encodeMethod);
            }

            // save the url Mapping
            shortUrl=  generatedCode;
            Url urlMapping = new Url();
            urlMapping.setShortUrl(shortUrl);
            urlMapping.setLongUrl(longUrl);
            urlRepository.save(urlMapping);
        }

        if (encodeMethod.equals("base62")){
            //generate SequenceId
            long sequenceId = redisSequenceService.getSequenceIdByScript();
            //generated a 6-letters code
            String generatedCode;
            generatedCode = UrlShortener.encodeBase62(sequenceId);


            // save the url Mapping
            shortUrl=  generatedCode;
            UrlBase62 urlMapping = new UrlBase62();

            urlMapping.setSequenceId(sequenceId);
            urlMapping.setLongUrl(longUrl);
            urlBase62Repository.save(urlMapping);
        }

        //save the url to Cache
        redisCacheService.insertUrl(shortUrl, longUrl);

        return shortUrl;
    }

    @Override
    @Transactional
    public String getByShortUrl(String shortUrl) {

        //try to get longUrl from redis
       String longUrl = redisCacheService.getUrl(shortUrl);


        if (longUrl != null){
            return  longUrl;
        }

        //decide decoding  method by length of shortUrl
        if(shortUrl.length() == 7){
            Url urlMapping = urlRepository.getByShortUrl(shortUrl);
            //check whether this short Url is in the database
            if (urlMapping==null){
                throw new UrlRequestException(HttpStatus.NOT_FOUND,"Unable to get original link. It is not a valid  shorter Url");
            }
            longUrl = urlMapping.getLongUrl();
        }


        if(shortUrl.length() == 6){
            long sequenceId = UrlShortener.decodeBase62(shortUrl);
            UrlBase62 urlMapping = urlBase62Repository.getBySequenceId(sequenceId);
            //check whether this short Url is in the database
            if (urlMapping==null){
                throw new UrlRequestException(HttpStatus.NOT_FOUND,"Unable to get original link. It is not a valid  shorter Url");
            }
            longUrl = urlMapping.getLongUrl();
        }

        if(longUrl == null){
            throw new UrlRequestException(HttpStatus.NOT_FOUND,"Unable to get original link. It is not a valid  shorter Url");
        }





        return longUrl;
    }


}
