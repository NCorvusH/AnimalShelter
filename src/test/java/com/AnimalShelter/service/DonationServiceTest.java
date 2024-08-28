package com.AnimalShelter.service;

import com.AnimalShelter.model.Donation;
import com.AnimalShelter.repository.IDonationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonationServiceTest implements AutoCloseable {

    @Mock
    private IDonationRepository iDonationRepository;

    @InjectMocks
    private DonationService donationService;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDonation() {
        Donation donation = new Donation();
        when(iDonationRepository.save(donation)).thenReturn(donation);

        Donation createdDonation = donationService.createDonation(donation);

        assertNotNull(createdDonation);
        verify(iDonationRepository, times(1)).save(donation);
    }

    @Test
    void testListDonations() {
        Donation donation1 = new Donation();
        Donation donation2 = new Donation();
        List<Donation> donationList = Arrays.asList(donation1, donation2);

        when(iDonationRepository.findAll()).thenReturn(donationList);

        List<Donation> result = donationService.getAllDonation();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(iDonationRepository, times(1)).findAll();
    }

    @Test
    void testGetDonationById() {
        int donationId = 1;
        Donation donation = new Donation();
        donation.setDonationId(donationId);

        when(iDonationRepository.findById(donationId)).thenReturn(Optional.of(donation));

        Optional<Donation> result = donationService.getDonationById(donationId);

        assertTrue(result.isPresent());
        assertEquals(donationId, result.get().getDonationId());
        verify(iDonationRepository, times(1)).findById(donationId);
    }

    @Test
    void testUpdateDonation() {
        int donationId = 1;
        Donation updatedDonation = new Donation();
        updatedDonation.setDonationId(donationId);

        when(iDonationRepository.save(updatedDonation)).thenReturn(updatedDonation);

        donationService.updateDonation(updatedDonation, donationId);

        verify(iDonationRepository, times(1)).save(updatedDonation);
    }

    @Test
    void testDeleteDonation() {
        int donationId = 1;

        doNothing().when(iDonationRepository).deleteById(donationId);

        boolean result = donationService.deleteDonation(donationId);

        assertTrue(result);
        verify(iDonationRepository, times(1)).deleteById(donationId);
    }

    @Test
    void testDeleteDonationException() {
        int donationId = 1;

        doThrow(new RuntimeException("Error deleting donation")).when(iDonationRepository).deleteById(donationId);

        boolean result = donationService.deleteDonation(donationId);

        assertFalse(result);
        verify(iDonationRepository, times(1)).deleteById(donationId);
    }

    @Override
    public void close() throws Exception {
        mocks.close();
    }
}