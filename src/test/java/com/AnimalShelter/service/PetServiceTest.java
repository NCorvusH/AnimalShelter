package com.AnimalShelter.service;

import com.AnimalShelter.model.Pet;
import com.AnimalShelter.repository.IPetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class PetServiceTest {

    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetService petService;

    private Pet pet1;
    private Pet pet2;
    private final ArrayList<Pet> petList = new ArrayList<>();

    @BeforeEach
    public void SetUp(){
        MockitoAnnotations.openMocks(this);

        pet1 = new Pet();
        pet1.setId(1);
        pet1.setName("Bolita");
        pet1.setAge(4);
        pet1.setBreed("Belier");
        //pet1.setSex("Male");
        pet1.setCategory("hola");
        pet1.setDescription("hola");
        //pet1.setAdopted();

        pet2 = new Pet();
        pet2.setId(2);
        pet2.setName("Lia");
        pet2.setAge(5);
        pet2.setBreed("Water Dog");
        //pet2.setSex("Female");
        pet2.setCategory("hola");
        pet2.setDescription("hola");
        //pet2.setAdopted();
    }
    @Test
    void updatePetTest(){
        when(iPetRepository.save(any(Pet.class))).thenReturn(pet1);

        Pet petToUpdate = new Pet();
        petToUpdate.setId(1);
        petToUpdate.setName("UpdatedBolita");
        petToUpdate.setAge(5);

        petService.updatePetById(petToUpdate, 1);

        assertEquals(1, petToUpdate.getId());
        verify(iPetRepository, times(1)).save(petToUpdate);
    }

    @Test
    void createPetTest() throws IOException{

        when(iPetRepository.save(any(Pet.class))).thenReturn(pet2);
        Pet newPet = petService.createPet(pet2);

        assertNotNull(newPet);
        assertNotNull(2, String.valueOf(newPet.getId()));
        assertNotNull("Lia", newPet.getName());
        assertNotNull(5, String.valueOf(newPet.getAge()));
        assertNotNull("Water dog", newPet.getBreed());
        assertNotNull("hola", newPet.getCategory());
        assertNotNull("hola", newPet.getDescription());
        //assertNotNull("Sí", newPet.getAdopted());

        verify(iPetRepository, times(1)).save(pet2);
    }
    @Test
    void deletePetByIdTest(){
        int id = 2;
        petService.deletePetById(id);
        verify(iPetRepository).deleteById(id);
    }

    @Test
    void getAllPetTest(){
        List<Pet> petList = new ArrayList<>();
        petList.add(pet1);
        petList.add(pet2);

        when(iPetRepository.findAll()).thenReturn(petList);

        List<Pet> allPet = petService.getAllPet();

        assertNotNull(allPet);
        assertEquals(2, allPet.size());
        assertTrue(allPet.contains(pet1));
        assertTrue(allPet.contains(pet2));
        verify(iPetRepository, times(1)).findAll();
    }
    @Test
    void getPetByIdTest(){
        when(iPetRepository.findById(1)).thenReturn(Optional.of(pet1));

        Optional<Pet> foundPet = petService.getPetById(1);

        assertTrue(foundPet.isPresent());
        assertEquals(pet1.getId(), foundPet.get().getId());
        assertEquals(pet1.getName(), foundPet.get().getName());
        verify(iPetRepository, times(1)).findById(1);
    }

}
