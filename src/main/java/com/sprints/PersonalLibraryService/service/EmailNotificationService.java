package com.sprints.PersonalLibraryService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Primary
public class EmailNotificationService implements NotificationService{

    @Value("${app.email}")
    private String globalEmail;

    @Override
    public String sendNotification(String message) {
        return "email sent by : " + globalEmail;
    }
}
