package com.sprints.PersonalLibraryService.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Qualifier("sms")
public class SmsNotificationService implements NotificationService {

    @Value("${app.phone}")
    private String phone;

    @Override
    public String sendNotification(String message) {
        return "SMS sent by : " + phone;
    }
}
