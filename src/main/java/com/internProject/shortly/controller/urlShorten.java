package com.internProject.shortly.controller;



import com.internProject.shortly.util.urlShortener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class urlShorten {

    @GetMapping("/shorten")
    public String getShortUrl(@RequestBody String longUrl){
        System.out.println(longUrl);
        String generatedCode = urlShortener.encodeRandom();

        return "http://Shortly.com/" + generatedCode;

    }
}
