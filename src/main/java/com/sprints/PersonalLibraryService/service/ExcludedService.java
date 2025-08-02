package com.sprints.PersonalLibraryService.service;

import org.springframework.stereotype.Service;

@Service
public class ExcludedService {
    
    public String getExcludedMessage() {
        return "This service should be excluded by component scan";
    }
} 