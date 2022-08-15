package com.internProject.shortly.util;


import java.util.Random;

/**
 *
 */
public class urlShortener {
    private static String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     *  this medthod produces short URLs like "KtLa2U",
     *  using a random code of six digits or letters.
     * @return a random generated short url
     */
    public  static String encodeRandom(){
        Random rand = new Random();
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            shortUrl.append(alphabet.charAt(rand.nextInt(62)));
        }
        return shortUrl.toString();

    }
}
