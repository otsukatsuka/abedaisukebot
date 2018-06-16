package com.otsukatsuka.daisukebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DaisukebotApplication {

	@RequestMapping("/")
	String Hello(){
		return "Hello";
	}
	public static void main(String[] args) {
		SpringApplication.run(DaisukebotApplication.class, args);
	}
}
