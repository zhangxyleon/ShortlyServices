package com.internProject.shortly.controller;


import com.internProject.shortly.exception.UrlRequestException;
import com.internProject.shortly.util.urlShortener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
public class UrlController {

    //Temporary variable Used for Request testing, will be replaced by database
    private int count = 0;
    private HashMap<String, String> urlMap = new HashMap<>();



    /**
     *  This method is a helper function to validate Url
     *
     * @param url a String of url
     * @return Returns true if url is valid. false otherwise
     */
    private static boolean isValid(String url)
    {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }

    /**
     * This method is a helper function to encode Url
     *
     * @param encodeMethod a String of encode method
     * @return  Return a String of size 6 based on the provided encode method
     */
    private static String encodeUrl(String encodeMethod) {

        String generatedCode = "";
        if (Objects.equals(encodeMethod, "random")) {
            // generated a random string of 6 letters
            generatedCode = urlShortener.encodeRandom();
        } else if ((Objects.equals(encodeMethod, "base62"))) {
            // convert a  number from base 10 to base 62
            int randomNum =(int)(Math.random() * (Integer.MAX_VALUE));
            generatedCode = urlShortener.encodeBase62(randomNum);
        }

        return generatedCode;
    }




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

        //check whether url is valid
        if (!isValid(longUrl)){
            throw new UrlRequestException(HttpStatus.BAD_REQUEST,"Unable to shorter that link. It is not a valid Url");
        }

        //check whether encoding method is valid
        if (!encodeMethod.equals("random") && (!encodeMethod.equals("base62")) ) {
            throw new UrlRequestException(HttpStatus.BAD_REQUEST,"Unable to shorter that link. It is not a valid encoding method");

        }

        //generated a 6-letters code
        String generatedCode;
        count++;
        generatedCode = encodeUrl(encodeMethod);
        //check whether this new short url is already used(In the future, this will be implemented by using database)
        while (urlMap.containsKey("http://localhost:8080/"+ generatedCode)){
            generatedCode = encodeUrl(encodeMethod);
        }

        // save the url Mapping
        String shortUrl= "http://localhost:8080/" + generatedCode;
        urlMap.put(shortUrl, longUrl);

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

        //check whether this short Url is in the database
        if (!urlMap.containsKey("http://localhost:8080/"+shortUrl)){
            throw new UrlRequestException(HttpStatus.NOT_FOUND,"Unable to get original link. It is not a valid  shorter Url");
        }



        return urlMap.get("http://localhost:8080/"+shortUrl);
    }



}
