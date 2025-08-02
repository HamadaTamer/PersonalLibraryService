package com.sprints.PersonalLibraryService.service;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    public String sendNotification(String message);
}
