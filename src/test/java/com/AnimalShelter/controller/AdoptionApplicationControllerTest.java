package com.AnimalShelter.controller;

import com.AnimalShelter.model.AdoptionApplication;
import com.AnimalShelter.service.AdoptionApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdoptionApplicationControllerTest {

    @Mock
    private AdoptionApplicationService adoptionApplicationService;

    @InjectMocks
    private AdoptionApplicationController adoptionApplicationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSubmitApplication() {
        AdoptionApplication application = new AdoptionApplication();
        when(adoptionApplicationService.submitApplication(application)).thenReturn(application);

        ResponseEntity<AdoptionApplication> response = adoptionApplicationController.submitApplication(application);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(application, response.getBody());
        verify(adoptionApplicationService, times(1)).submitApplication(application);
    }

    @Test
    void testViewApplication() {
        int applicationId = 1;
        AdoptionApplication application = new AdoptionApplication();
        application.setApplicationId(applicationId);

        when(adoptionApplicationService.viewApplication(applicationId)).thenReturn(Optional.of(application));

        ResponseEntity<AdoptionApplication> response = adoptionApplicationController.viewApplication(applicationId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(application, response.getBody());
        verify(adoptionApplicationService, times(1)).viewApplication(applicationId);
    }

    @Test
    void testUpdateApplication() {
        int applicationId = 1;
        AdoptionApplication updateApplication = new AdoptionApplication();

        when(adoptionApplicationService.updateApplication(applicationId, updateApplication)).thenReturn(Optional.of(updateApplication));

        ResponseEntity<AdoptionApplication> response = adoptionApplicationController.updateApplication(applicationId, updateApplication);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updateApplication, response.getBody());
        verify(adoptionApplicationService, times(1)).updateApplication(applicationId, updateApplication);
    }

    @Test
    void testDeleteApplication() {
        int applicationId = 1;

        when(adoptionApplicationService.deleteApplication(applicationId)).thenReturn(true);

        ResponseEntity<Void> response = adoptionApplicationController.deleteApplication(applicationId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(adoptionApplicationService, times(1)).deleteApplication(applicationId);
    }

    @Test
    void testListApplications() {
        AdoptionApplication application1 = new AdoptionApplication();
        AdoptionApplication application2 = new AdoptionApplication();
        List<AdoptionApplication> applications = Arrays.asList(application1, application2);

        when(adoptionApplicationService.listApplications()).thenReturn(applications);

        ResponseEntity<List<AdoptionApplication>> response = adoptionApplicationController.listApplications();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(applications, response.getBody());
        verify(adoptionApplicationService, times(1)).listApplications();
    }
}