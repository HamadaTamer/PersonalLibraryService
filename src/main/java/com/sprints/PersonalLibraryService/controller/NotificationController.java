package com.sprints.PersonalLibraryService.controller;

import com.sprints.PersonalLibraryService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotificationController {
    
    // Primary service (EmailNotificationService)
    private final NotificationService primaryNotificationService;
    
    // Qualified service (SmsNotificationService)
    private final NotificationService smsNotificationService;

    @Autowired
    public NotificationController(
            NotificationService primaryNotificationService,
            @Qualifier("sms") NotificationService smsNotificationService) {
        this.primaryNotificationService = primaryNotificationService;
        this.smsNotificationService = smsNotificationService;
    }

    @GetMapping("/email")
    public String sendEmail(@RequestParam(defaultValue = "Hello from email service") String message) {
        return primaryNotificationService.sendNotification(message);
    }

    @GetMapping("/sms")
    public String sendSms(@RequestParam(defaultValue = "Hello from SMS service") String message) {
        return smsNotificationService.sendNotification(message);
    }
}
