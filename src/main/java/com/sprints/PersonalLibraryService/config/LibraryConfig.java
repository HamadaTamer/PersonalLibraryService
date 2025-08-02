package com.sprints.PersonalLibraryService.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "library")
public class LibraryConfig {
    
    private String name;
    private String mode;
} 