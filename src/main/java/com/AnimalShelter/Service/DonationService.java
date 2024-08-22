package com.AnimalShelter.Service;

import com.AnimalShelter.Model.Donation;
import com.AnimalShelter.Repository.IDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private IDonationRepository iDonationRepository;

    public Donation createDonation(Donation donation) {
        return iDonationRepository.save(donation);
    }

    public List<Donation> listDonations() {
        try {
            return iDonationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving donations.", e);
        }
    }

    public Optional<Donation> viewDonationDetails(int donationId) {
        try {
            return iDonationRepository.findById(donationId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving donation details.", e);
        }
    }

    public void editDonation(int donationId, Donation updateDonation) {
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