package com.sprints.PersonalLibraryService.service;

import com.sprints.PersonalLibraryService.config.LibraryConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryService {
    
    private final LibraryConfig libraryConfig;
    
    public String getLibraryInfo() {
        log.info("Library Service - Name: {}, Mode: {}", libraryConfig.getName(), libraryConfig.getMode());
        log.debug("Debug message: Library configuration loaded successfully");
        log.warn("Warning message: This is a sample warning");
        log.error("Error message: This is a sample error");
        
        return String.format("Library: %s (Mode: %s)", libraryConfig.getName(), libraryConfig.getMode());
    }
    
    public void logConfigurationDetails() {
        log.info("=== Library Configuration Details ===");
        log.info("Library Name: {}", libraryConfig.getName());
        log.info("Library Mode: {}", libraryConfig.getMode());
        log.info("Active Profile: {}", System.getProperty("spring.profiles.active", "default"));
        log.info("=====================================");
    }
} 