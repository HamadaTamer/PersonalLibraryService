package com.sprints.PersonalLibraryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PersonalLibraryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalLibraryServiceApplication.class, args);
	}

}
