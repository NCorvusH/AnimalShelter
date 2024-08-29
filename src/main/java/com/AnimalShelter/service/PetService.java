package com.AnimalShelter.service;

import com.AnimalShelter.model.Pet;
import com.AnimalShelter.model.PetDto;
import com.AnimalShelter.repository.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    private Pet convertDTOToEntity (PetDto petDTO) throws IOException {
        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setAge(petDTO.getAge());
        pet.setBreed(petDTO.getBreed());
        pet.setGender(petDTO.getGender());
        pet.setSex(petDTO.getSex());
        pet.setCategory(petDTO.getCategory());
        pet.setDescription(petDTO.getDescription());
        pet.setAdopted(petDTO.isAdopted());


        if (petDTO.getPhoto() != null && !petDTO.getPhoto().isEmpty()) {
            pet.setPhoto(petDTO.getPhoto().getBytes());
        }
        return pet;
    }
}