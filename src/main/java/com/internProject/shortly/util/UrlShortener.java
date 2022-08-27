package com.internProject.shortly.util;


import java.util.Objects;
import java.util.Random;

/**
 *
 */
public class UrlShortener {
    private static String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    /**
     * This method is a helper function to encode Url
     *
     * @param encodeMethod a String of encode method
     * @return  Return a String of size 6 based on the provided encode method
     */
    public static String encodeUrl(String encodeMethod) {

        String generatedCode = "";
        if (Objects.equals(encodeMethod, "random")) {
            // generated a random string of 6 letters
            generatedCode = encodeRandom();
        } else if ((Objects.equals(encodeMethod, "base62"))) {
            // convert a  number from base 10 to base 62
            int randomNum = (int) (Math.random() * (Integer.MAX_VALUE));
            generatedCode = encodeBase62(randomNum);
        }

        return generatedCode;
    }


    /**
     *  this medthod produces short URLs like "KtLa2U",
     *  using a random code of six digits or letters.
     * @return a random generated short url
     */
    public static String encodeRandom(){
        Random rand = new Random();
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            shortUrl.append(alphabet.charAt(rand.nextInt(62)));
        }
        return shortUrl.toString();

    }

    /**
     * this method convert a base10 number to a string of base62 number
     * @param base10 an int
     * @return a  6-letters string of a base 62 number
     */
    public static String encodeBase62(Integer base10){
        StringBuilder base62 = new StringBuilder();
        while(base10!=0){
            base62.append(alphabet.charAt(base10%62));
            base10/=62;
        }
        while(base62.length()<6){
            base62.append(0);
        }
        return base62.reverse().toString();
    }



    public static Integer decodeBase62(String base62){
        int base10 = 0;
        for (int i = 0; i<base62.length();i++){
            int value = alphabet.indexOf((char)base62.charAt(i));
            base10 += (int)(Math.pow(62,5-i) * value);
        }
        return base10;
    }

}
