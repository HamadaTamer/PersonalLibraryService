package com.sprints.PersonalLibraryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(
    basePackages = "com.sprints.PersonalLibraryService",
    includeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = ".*Service|.*Controller|.*Config"
    ),
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = ".*Excluded.*"
    )
)
public class PersonalLibraryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalLibraryServiceApplication.class, args);
	}

}
