package com.sprints.PersonalLibraryService.controller;

import com.sprints.PersonalLibraryService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping("/email")
    public String sendEmail(String message){
        return notificationService.sendNotification(message);
    }

    @GetMapping("/sms")
    public String sendSms(String message){
        return notificationService.sendNotification(message);
    }

}
