package com.internProject.shortly.redis;


import com.internProject.shortly.entity.Url;

public interface RedisCacheService {

    /**
     * return Ture if inserting to redis is successful
     * @param shortUrl
     * @param longUrl
     * @return
     */
    void insertUrl(String shortUrl, String longUrl);

    /**
     * get longUrl mapped by shortUrl
     * @param shortUrl
     * @return
     */
    String getUrl(String shortUrl);

    /**
     *
     * @param shortUrl
     * @return
     */
    void deleteUrl(String shortUrl);


    void setTimeOut(String key, long timeout);

}
