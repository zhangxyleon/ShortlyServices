package com.internProject.shortly.controller;



import com.internProject.shortly.exception.UrlRequestException;
import com.internProject.shortly.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;

import java.util.Map;


@RestController
public class UrlController {


    @Autowired
    private UrlService urlService;



    /**
     * This method will handle request for url Shortening
     *
     * @param UrlRequest  consists of origin Url and encode method,
     *                    example JSON: {"longUrl":  "Url", "encodeMethod":"Random"}
     * @return generated short Url based on encode method chosen by user
     */
    @PostMapping("/shorten")
    @ResponseBody
    public String createShortUrl(@RequestBody Map<String, String> UrlRequest,  HttpServletResponse response){

        //deconstruct Request body
        String longUrl = UrlRequest.get("longUrl");
        String  encodeMethod = UrlRequest.get("encodeMethod");

        String shortUrl = urlService.createShortUrl(longUrl, encodeMethod);

        return shortUrl;

    }

    /**
     * This method is to get  by origin url mapped by short url.
     *
     * @param shortUrl
     * @return origin url mapped by the given shortUrl
     */
    @GetMapping("/{shortUrl}")
    public String getLongUrl(@PathVariable("shortUrl") String shortUrl){

        return  urlService.getByShortUrl("http://localhost:8080/"+shortUrl);
    }



}
