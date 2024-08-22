package com.AnimalShelter.Controller;

import com.AnimalShelter.Model.Pet;
import com.AnimalShelter.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    PetService petService;

@PostMapping(path = "/Pet")
    public Pet createPet(@RequestBody Pet newPet){
    return petService.createPet(newPet);
}

@GetMapping(path = "")
    public ArrayList<Pet> getAllPet(){
    return petService.getAllPet();
}

@GetMapping(path ="/Pet/{id}")
    public Pet getPetId(@PathVariable int id){
    return petService.getPetId(id);
}

@DeleteMapping(path = "/Pet/{id}")
    public ResponseEntity<void> deletePetById(@PathVariable int id) {
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
@PutMapping(path ="/Pet/{id}")
    public void updatePetById(@RequestBody Pet pet, @PathVariable int id){
    petService.updatePetById(pet, id);
}

}
