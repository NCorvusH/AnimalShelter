package com.AnimalShelter.service;

import com.AnimalShelter.model.AdoptionApplication;
import com.AnimalShelter.repository.IAdoptionApplicationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdoptionApplicationTest {

    @Mock
    private IAdoptionApplicationRepository adoptionApplicationRepository;

    @InjectMocks
    private AdoptionApplicationService adoptionApplicationService;

    @Test
    void testSubmitApplication() {
        AdoptionApplication application = new AdoptionApplication();
        when(adoptionApplicationRepository.save(application)).thenReturn(application);

        AdoptionApplication createdApplication = adoptionApplicationService.submitApplication(application);

        assertNotNull(createdApplication);
        verify(adoptionApplicationRepository, times(1)).save(application);
    }

    @Test
    void testViewApplication() {
        int applicationId = 1;
        AdoptionApplication application = new AdoptionApplication();
        application.setApplicationId(applicationId);

        when(adoptionApplicationRepository.findById(applicationId)).thenReturn(Optional.of(application));

        Optional<AdoptionApplication> result = adoptionApplicationService.viewApplication(applicationId);

        assertTrue(result.isPresent());
        assertEquals(applicationId, result.get().getApplicationId());
        verify(adoptionApplicationRepository, times(1)).findById(applicationId);
    }

    @Test
    void testUpdateApplication() {
        int applicationId = 1;
        AdoptionApplication updateApplication = new AdoptionApplication();
        updateApplication.setApplicationId(applicationId);

        when(adoptionApplicationRepository.existsById(applicationId)).thenReturn(true);
        when(adoptionApplicationRepository.save(updateApplication)).thenReturn(updateApplication);

        Optional<AdoptionApplication> updatedApplication = adoptionApplicationService.updateApplication(applicationId, updateApplication);

        assertTrue(updatedApplication.isPresent());
        verify(adoptionApplicationRepository, times(1)).existsById(applicationId);
        verify(adoptionApplicationRepository, times(1)).save(updateApplication);
    }

    @Test
    void testDeleteApplication() {
        int applicationId = 1;

        when(adoptionApplicationRepository.existsById(applicationId)).thenReturn(true);
        doNothing().when(adoptionApplicationRepository).deleteById(applicationId);

        boolean result = adoptionApplicationService.deleteApplication(applicationId);

        assertTrue(result);
        verify(adoptionApplicationRepository, times(1)).existsById(applicationId);
        verify(adoptionApplicationRepository, times(1)).deleteById(applicationId);
    }

    @Test
    void testListApplications() {
        AdoptionApplication application1 = new AdoptionApplication();
        AdoptionApplication application2 = new AdoptionApplication();
        List<AdoptionApplication> applications = Arrays.asList(application1, application2);

        when(adoptionApplicationRepository.findAll()).thenReturn(applications);

        List<AdoptionApplication> result = adoptionApplicationService.listApplications();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(adoptionApplicationRepository, times(1)).findAll();
    }
}