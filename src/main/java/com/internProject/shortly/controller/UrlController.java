package com.internProject.shortly.controller;




import com.internProject.shortly.annotation.RateLimit;
import com.internProject.shortly.entity.Url;
import com.internProject.shortly.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


import java.util.Map;


@Controller
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
    @RateLimit(permitsPerSecond = 0.1)
    public String createShortUrl(@RequestBody Map<String, String> UrlRequest,  HttpServletResponse response){

        //deconstruct Request body
        String longUrl = UrlRequest.get("longUrl");
        String  encodeMethod = UrlRequest.get("encodeMethod");

        String shortUrl = urlService.createShortUrl(longUrl, encodeMethod);

        return "http://localhost:8081/" + shortUrl;

    }



    @GetMapping("/url")
    @ResponseBody
    public String test(){

        return "authenticated";

    }


    @GetMapping("/callback")
    @ResponseBody
    public String callback(){

        return "callback";

    }

    /**
     * This method is to redirect  by origin url mapped by short url.
     *
     * @param shortUrl
     * @return redirect to origin url
     */
    @GetMapping("/{shortUrl}")
    @RateLimit(permitsPerSecond = 0.1)
    public String getLongUrl(@PathVariable("shortUrl") String shortUrl){

        return  "redirect:"+urlService.getByShortUrl(shortUrl);
    }


}
