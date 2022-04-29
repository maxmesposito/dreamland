package com.dreamland.dreamtoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DreamtokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamtokenApplication.class, args);
	}

}
