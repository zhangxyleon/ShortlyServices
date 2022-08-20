package com.internProject.shortly;

import com.internProject.shortly.entity.Url;
import com.internProject.shortly.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShortlyApplicationTests {

	@Test
	void contextLoads() {
	}


//	@Autowired
//	private UrlService urlService;
//	@Test
//	void testCreateUrl(){
//		Url url= new Url();
//		url.setLongUrl("longUrl");
//		url.setShortUrl("asdshortUrl");
//
//		urlService.createUrl(url);
//	}
//
//
//	@Test
//	void testGetByShortUrl(){
//
//		Url url = urlService.getByShortUrl("shortUrl");
//
//		System.out.println(url);
//	}
}
