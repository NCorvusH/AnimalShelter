package com.AnimalShelter.Service;

import com.AnimalShelter.Model.Pet;
import com.AnimalShelter.Repository.IPetRepository;
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

    public List<Pet> getAllPets() {
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

    public void updatePet(Pet pet, int id) {
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
