package com.quizpro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringQuizProApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringQuizProApplication.class);
	public static void main(String[] args) {
		log.info("SPRING APPLICATION STARTED");
		SpringApplication.run(SpringQuizProApplication.class, args);
		log.info("SPRING APPLICATION ENDS");
	}
}
