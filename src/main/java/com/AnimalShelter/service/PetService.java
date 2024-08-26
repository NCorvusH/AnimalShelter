package com.AnimalShelter.service;

import com.AnimalShelter.model.Pet;
import com.AnimalShelter.repository.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    IPetRepository iPetRepository;

    public Pet createPet(Pet pet) {
        return iPetRepository.save(pet);
    }

    public List<Pet> getAllPet() {
        try {
            return iPetRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving pets.", e);
        }
    }

    public Optional<Pet> getPetById(int id) {
        try {
            return iPetRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving pet by id", e);
        }
    }

    public void updatePetById(Pet pet, int id) {
        pet.setId(id);
        iPetRepository.save(pet);
    }

    public boolean deletePetById(int id) {
        try {
            iPetRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
