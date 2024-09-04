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

    public Optional<Donation> getDonationById (int donationId) {
        try {
            return iDonationRepository.findById(donationId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving donation details.", e);
        }
    }

    public void updateDonation(Donation donation, int Id) {
        donation.setId(Id);
        iDonationRepository.save(donation);
    }

    public boolean deleteDonation(int Id) {
        try {
            iDonationRepository.deleteById(Id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}