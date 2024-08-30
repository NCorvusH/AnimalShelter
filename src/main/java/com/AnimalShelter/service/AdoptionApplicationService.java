package com.AnimalShelter.service;

import com.AnimalShelter.model.AdoptionApplication;
import com.AnimalShelter.repository.IAdoptionApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionApplicationService {

    private final IAdoptionApplicationRepository adoptionApplicationRepository;

    @Autowired
    public AdoptionApplicationService(IAdoptionApplicationRepository adoptionApplicationRepository) {
        this.adoptionApplicationRepository = adoptionApplicationRepository;
    }

    public AdoptionApplication submitApplication(AdoptionApplication application) {
        return adoptionApplicationRepository.save(application);
    }

    public Optional<AdoptionApplication> viewApplication(int applicationId) {
        return adoptionApplicationRepository.findById(applicationId);
    }

    public Optional<AdoptionApplication> updateApplication(int applicationId, AdoptionApplication updateApplication) {
        if (adoptionApplicationRepository.existsById(applicationId)) {
            updateApplication.setApplicationId(applicationId);
            return Optional.of(adoptionApplicationRepository.save(updateApplication));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteApplication(int applicationId) {
        if (adoptionApplicationRepository.existsById(applicationId)) {
            adoptionApplicationRepository.deleteById(applicationId);
            return true;
        } else {
            return false;
        }
    }

    public List<AdoptionApplication> listApplications() {
        return adoptionApplicationRepository.findAll();
    }
}