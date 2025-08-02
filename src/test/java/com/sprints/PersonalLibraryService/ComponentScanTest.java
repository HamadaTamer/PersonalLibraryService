package com.sprints.PersonalLibraryService;

import com.sprints.PersonalLibraryService.service.ExcludedService;
import com.sprints.PersonalLibraryService.service.GreetingService;
import com.sprints.PersonalLibraryService.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ComponentScanTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testIncludedServicesAreLoaded() {
        // Test that included services are loaded
        assertTrue(applicationContext.containsBean("libraryService"));
        assertTrue(applicationContext.containsBean("morningGreetingService"));
        assertTrue(applicationContext.containsBean("eveningGreetingService"));
        assertTrue(applicationContext.containsBean("emailNotificationService"));
        assertTrue(applicationContext.containsBean("smsNotificationService"));
        
        // Verify we can get the beans
        LibraryService libraryService = applicationContext.getBean(LibraryService.class);
        assertNotNull(libraryService);
        
        GreetingService morningService = applicationContext.getBean("morningGreetingService", GreetingService.class);
        assertNotNull(morningService);
    }

    @Test
    void testExcludedServiceIsNotLoaded() {
        // Test that excluded service is NOT loaded
        assertFalse(applicationContext.containsBean("excludedService"));
        
        // Verify we cannot get the excluded bean
        assertThrows(org.springframework.beans.factory.NoSuchBeanDefinitionException.class, () -> {
            applicationContext.getBean(ExcludedService.class);
        });
    }

    @Test
    void testControllersAreLoaded() {
        // Test that controllers are loaded
        assertTrue(applicationContext.containsBean("greetingController"));
        assertTrue(applicationContext.containsBean("healthController"));
        assertTrue(applicationContext.containsBean("libraryController"));
        assertTrue(applicationContext.containsBean("notificationController"));
    }

    @Test
    void testConfigIsLoaded() {
        // Test that config is loaded
        assertTrue(applicationContext.containsBean("libraryConfig"));
    }
} 