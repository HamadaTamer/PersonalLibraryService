package com.sprints.PersonalLibraryService.service;

import org.springframework.stereotype.Service;

@Service("eveningGreetingService")
public class EveningGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Good Evening!";
    }
}