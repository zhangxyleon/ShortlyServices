package com.internProject.shortly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShortlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortlyApplication.class, args);
	}

}
