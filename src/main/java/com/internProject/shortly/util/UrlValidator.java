package com.internProject.shortly.util;

import java.net.URL;

public class UrlValidator {


    /**
     *  This method is a helper function to validate Url
     *
     * @param url a String of url
     * @return Returns true if url is valid. false otherwise
     */
    public static boolean isUrlValid(String url){
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
}
