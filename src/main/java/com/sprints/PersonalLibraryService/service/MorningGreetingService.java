package com.sprints.PersonalLibraryService.service;

import org.springframework.stereotype.Service;

@Service("morningGreetingService")
public class MorningGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Good Morning!";
    }
}