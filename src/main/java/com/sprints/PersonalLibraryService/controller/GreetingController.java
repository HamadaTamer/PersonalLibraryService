package com.sprints.PersonalLibraryService.controller;

import com.sprints.PersonalLibraryService.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GreetingController {

    private final LibraryService libraryService;

    @GetMapping("/greet")
    public String greet() {
        log.info("Greeting endpoint called");
        return "Hello from Personal Library Service! " + libraryService.getLibraryInfo();
    }

    @GetMapping("/greet/simple")
    public String simpleGreet() {
        return "Welcome to the Library Service!";
    }
}