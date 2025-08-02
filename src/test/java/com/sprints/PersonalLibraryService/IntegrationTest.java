package com.sprints.PersonalLibraryService;

import com.sprints.PersonalLibraryService.config.LibraryConfig;
import com.sprints.PersonalLibraryService.service.GreetingService;
import com.sprints.PersonalLibraryService.service.LibraryService;
import com.sprints.PersonalLibraryService.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private LibraryConfig libraryConfig;

    @Autowired
    private LibraryService libraryService;

    @Test
    void testApplicationContextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    void testLibraryConfigIsLoaded() {
        assertNotNull(libraryConfig);
        assertNotNull(libraryConfig.getName());
        assertNotNull(libraryConfig.getMode());
        System.out.println("Library Config - Name: " + libraryConfig.getName() + ", Mode: " + libraryConfig.getMode());
    }

    @Test
    void testLibraryServiceWorks() {
        assertNotNull(libraryService);
        String info = libraryService.getLibraryInfo();
        assertNotNull(info);
        assertTrue(info.contains(libraryConfig.getName()));
        assertTrue(info.contains(libraryConfig.getMode()));
        System.out.println("Library Service Info: " + info);
    }

    @Test
    void testGreetingServicesAreLoaded() {
        // Test MorningGreetingService
        GreetingService morningService = applicationContext.getBean("morningGreetingService", GreetingService.class);
        assertNotNull(morningService);
        assertEquals("Good Morning!", morningService.greet());

        // Test EveningGreetingService
        GreetingService eveningService = applicationContext.getBean("eveningGreetingService", GreetingService.class);
        assertNotNull(eveningService);
        assertEquals("Good Evening!", eveningService.greet());
    }

    @Test
    void testNotificationServicesAreLoaded() {
        // Test EmailNotificationService (Primary)
        NotificationService emailService = applicationContext.getBean(NotificationService.class);
        assertNotNull(emailService);
        assertTrue(emailService.sendNotification("test").contains("email sent by"));

        // Test SmsNotificationService (Qualified)
        NotificationService smsService = applicationContext.getBean("sms", NotificationService.class);
        assertNotNull(smsService);
        assertTrue(smsService.sendNotification("test").contains("SMS sent by"));
    }

    @Test
    void testControllersAreLoaded() {
        // Test all controllers are loaded
        assertTrue(applicationContext.containsBean("greetingController"));
        assertTrue(applicationContext.containsBean("healthController"));
        assertTrue(applicationContext.containsBean("libraryController"));
        assertTrue(applicationContext.containsBean("notificationController"));
    }

    @Test
    void testExcludedServiceIsNotLoaded() {
        // Verify ExcludedService is NOT loaded due to component scan filter
        assertFalse(applicationContext.containsBean("excludedService"));
    }
} 