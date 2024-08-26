package com.AnimalShelter.service;

import com.AnimalShelter.model.Donation;
import com.AnimalShelter.repository.IDonationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    private final IDonationRepository iDonationRepository;

    public DonationService(IDonationRepository iDonationRepository) {
        this.iDonationRepository = iDonationRepository;
    }

    public Donation createDonation(Donation donation) {
        return iDonationRepository.save(donation);
    }

    public List<Donation> getAllDonation() {
        try {
            return iDonationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving donations.", e);
        }
    }

    public Optional<Donation> getDonationByID (int id) {
        try {
            return iDonationRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving donation details.", e);
        }
    }

    public void updateDonation(int donationId, Donation updateDonation) {
        updateDonation.setDonationId(donationId);
        iDonationRepository.save(updateDonation);
    }

    public boolean deleteDonation(int donationId) {
        try {
            iDonationRepository.deleteById(donationId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}