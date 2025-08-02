package com.sprints.PersonalLibraryService.controller;

import com.sprints.PersonalLibraryService.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {
    
    private final LibraryService libraryService;
    
    @GetMapping("/info")
    public String getLibraryInfo() {
        log.info("GET /api/library/info - Request received");
        libraryService.logConfigurationDetails();
        return libraryService.getLibraryInfo();
    }
    
    @GetMapping("/config")
    public String getConfiguration() {
        log.info("GET /api/library/config - Configuration request received");
        libraryService.logConfigurationDetails();
        return "Configuration details logged. Check console for output.";
    }
} 