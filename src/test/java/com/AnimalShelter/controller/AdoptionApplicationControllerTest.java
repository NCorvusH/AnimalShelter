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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdoptionApplicationControllerTest {

    @Mock
    private AdoptionApplicationService adoptionApplicationService;
    private MockMvc mockMvc;
    private AdoptionApplication adoptionApplication1;
    private AdoptionApplication adoptionApplication2;

    @InjectMocks
    private AdoptionApplicationController adoptionApplicationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adoptionApplicationController).build();

        adoptionApplication1 = new AdoptionApplication();
        adoptionApplication1.setApplicationId(1);
        adoptionApplication1.setUserId(1);
        adoptionApplication1.setPetId(1);
        adoptionApplication1.setDateSubmitted(LocalDate.of(2024, 05, 05));
        adoptionApplication1.setApplicantName("Diego");
        adoptionApplication1.setApplicantEmail("Diego@diego.diego");
        adoptionApplication1.setApplicantPhone("+34 123456789");
        adoptionApplication1.setCountry("España");
        adoptionApplication1.setCity("Cuenca");
        adoptionApplication1.setAddressLine1("Arriba");
        adoptionApplication1.setAddressLine2("Izquierda");
        adoptionApplication1.setAddressLine3("7ºB");
        adoptionApplication1.setZipCode(12345);
        adoptionApplication1.setReasonForAdoption("Want pet please");
        adoptionApplication1.setPreviousPets("None");
        adoptionApplication1.setComments("Lonely petless sad");

        adoptionApplication2 = new AdoptionApplication();
        adoptionApplication2.setApplicationId(1);
        adoptionApplication2.setUserId(1);
        adoptionApplication2.setPetId(1);
        adoptionApplication2.setDateSubmitted(LocalDate.of(2024, 05, 05));
        adoptionApplication2.setApplicantName("Cocoliso");
        adoptionApplication2.setApplicantEmail("yes@good.ok");
        adoptionApplication2.setApplicantPhone("+34 987654321");
        adoptionApplication2.setCountry("Marte");
        adoptionApplication2.setCity("Sevilla");
        adoptionApplication2.setAddressLine1("Bepp");
        adoptionApplication2.setAddressLine2("Boop");
        adoptionApplication2.setAddressLine3("");
        adoptionApplication2.setZipCode(67890);
        adoptionApplication2.setReasonForAdoption("It's my hobby");
        adoptionApplication2.setPreviousPets("70000 nameless cats");
        adoptionApplication2.setComments("One more to the collection");
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