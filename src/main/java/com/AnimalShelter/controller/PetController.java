package com.AnimalShelter.controller;

import com.AnimalShelter.model.Pet;
import com.AnimalShelter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/news")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    PetService petService;

@PostMapping(path = "/api/v1/news")
    public Pet createPet(@RequestBody Pet newPet){
    return petService.createPet(newPet);
}

@GetMapping(path = "/api/v1/news")
    public List<Pet> getAllPet(){
    return petService.getAllPet();
}

@GetMapping(path ="/api/v1/news/{id}")
    public Optional<Pet> getPetById(@PathVariable int id){
    return petService.getPetById(id);
}

@DeleteMapping(path = "/api/v1/news/{id}")
    public ResponseEntity<Void> deletePetById(@PathVariable int id) {
    try {
        boolean delete = petService.deletePetById(id);
        if (delete) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
@PutMapping(path ="/api/v1/news/{id}")
    public void updatePetById(@RequestBody Pet pet, @PathVariable int id){
    petService.updatePetById(pet, id);
}

}

