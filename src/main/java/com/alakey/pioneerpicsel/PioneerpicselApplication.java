package com.alakey.pioneerpicsel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PioneerpicselApplication {

	public static void main(String[] args) {
		SpringApplication.run(PioneerpicselApplication.class, args);
	}

}
