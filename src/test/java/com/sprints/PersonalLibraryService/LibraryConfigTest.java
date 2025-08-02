package com.sprints.PersonalLibraryService;

import com.sprints.PersonalLibraryService.config.LibraryConfig;
import com.sprints.PersonalLibraryService.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryConfigTest {

    @Autowired
    private LibraryConfig libraryConfig;
    
    @Autowired
    private LibraryService libraryService;

    @Test
    void testDefaultProfileConfiguration() {
        assertNotNull(libraryConfig);
        assertNotNull(libraryConfig.getName());
        assertNotNull(libraryConfig.getMode());
        
        System.out.println("Default Profile Test:");
        System.out.println("Library Name: " + libraryConfig.getName());
        System.out.println("Library Mode: " + libraryConfig.getMode());
        
        String info = libraryService.getLibraryInfo();
        assertNotNull(info);
        assertTrue(info.contains(libraryConfig.getName()));
        assertTrue(info.contains(libraryConfig.getMode()));
    }
}

@SpringBootTest
@ActiveProfiles("dev")
class LibraryConfigDevTest {

    @Autowired
    private LibraryConfig libraryConfig;
    
    @Autowired
    private LibraryService libraryService;

    @Test
    void testDevProfileConfiguration() {
        assertNotNull(libraryConfig);
        assertEquals("Development Library", libraryConfig.getName());
        assertEquals("development", libraryConfig.getMode());
        
        System.out.println("Dev Profile Test:");
        System.out.println("Library Name: " + libraryConfig.getName());
        System.out.println("Library Mode: " + libraryConfig.getMode());
        
        String info = libraryService.getLibraryInfo();
        assertNotNull(info);
        assertTrue(info.contains("Development Library"));
        assertTrue(info.contains("development"));
    }
}

@SpringBootTest
@ActiveProfiles("prod")
class LibraryConfigProdTest {

    @Autowired
    private LibraryConfig libraryConfig;
    
    @Autowired
    private LibraryService libraryService;

    @Test
    void testProdProfileConfiguration() {
        assertNotNull(libraryConfig);
        assertEquals("Production Library", libraryConfig.getName());
        assertEquals("production", libraryConfig.getMode());
        
        System.out.println("Prod Profile Test:");
        System.out.println("Library Name: " + libraryConfig.getName());
        System.out.println("Library Mode: " + libraryConfig.getMode());
        
        String info = libraryService.getLibraryInfo();
        assertNotNull(info);
        assertTrue(info.contains("Production Library"));
        assertTrue(info.contains("production"));
    }
} 