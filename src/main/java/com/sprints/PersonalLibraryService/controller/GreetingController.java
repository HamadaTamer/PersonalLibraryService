package com.sprints.PersonalLibraryService.controller;

import com.sprints.PersonalLibraryService.service.GreetingService;
import com.sprints.PersonalLibraryService.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    
    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    // Field Injection
    @Autowired
    @Qualifier("morningGreetingService")
    private GreetingService morningGreetingService;

    // Constructor Injection
    private final GreetingService eveningGreetingService;
    private final LibraryService libraryService;

    // Setter Injection
    private GreetingService defaultGreetingService;

    public GreetingController(
            @Qualifier("eveningGreetingService") GreetingService eveningGreetingService,
            LibraryService libraryService) {
        this.eveningGreetingService = eveningGreetingService;
        this.libraryService = libraryService;
    }

    @Autowired
    public void setDefaultGreetingService(@Qualifier("morningGreetingService") GreetingService defaultGreetingService) {
        this.defaultGreetingService = defaultGreetingService;
    }

    @GetMapping("/greet")
    public String greet() {
        log.info("Greeting endpoint called");
        return "Hello from Personal Library Service! " + libraryService.getLibraryInfo();
    }

    @GetMapping("/greet/simple")
    public String simpleGreet() {
        return "Welcome to the Library Service!";
    }

    // Constructor Injection Demo
    @GetMapping("/greet/evening")
    public String eveningGreet() {
        return eveningGreetingService.greet() + " " + libraryService.getLibraryInfo();
    }

    // Field Injection Demo
    @GetMapping("/greet/morning")
    public String morningGreet() {
        return morningGreetingService.greet() + " " + libraryService.getLibraryInfo();
    }

    // Setter Injection Demo
    @GetMapping("/greet/default")
    public String defaultGreet() {
        return defaultGreetingService.greet() + " " + libraryService.getLibraryInfo();
    }
}