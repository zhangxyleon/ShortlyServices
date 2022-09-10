package com.internProject.shortly.DAO;


import com.internProject.shortly.entity.Url;
import org.springframework.data.repository.CrudRepository;


public interface UrlRepository extends CrudRepository<Url, Long> {

    Url getByShortUrl(String shortUrl);

}
