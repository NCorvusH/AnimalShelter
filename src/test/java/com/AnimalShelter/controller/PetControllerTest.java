package com.AnimalShelter.controller;

import com.AnimalShelter.model.Pet;
import com.AnimalShelter.service.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    private MockMvc mockMvc;
    private Pet pet1;
    private Pet pet2;
    private ArrayList<Pet> petList;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();

        pet1 = new Pet();
        pet1.setId(1);
        pet1.setName("Koda");
        pet1.setAge(1);
        pet1.setBreed("Mestizo");
        pet1.setGender("Macho");
        pet1.setCategory("Perro");
        pet1.setDescription("Lo que sea");
        pet1.setAdopted(false);

        pet2 = new Pet();
        pet2.setId(2);
        pet2.setName("lia");
        pet2.setAge(4);
        pet2.setBreed("siames");
        pet2.setGender("hembra");
        pet2.setCategory("gato");
        pet2.setDescription("gato de 4 patas");
        pet2.setAdopted(true);

    }

    @Test
    void createTestPet() throws Exception {
        when(petService.createPet(any(Pet.class))).thenReturn(pet1);

        ObjectMapper objectMapper = new ObjectMapper();
        String petJson = objectMapper.writeValueAsString(pet1);

        mockMvc.perform(post("/api/v1/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(petJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Koda"))
                .andExpect(jsonPath("$.age").value(1))
                .andExpect(jsonPath("$.breed").value("Mestizo"))
                .andExpect(jsonPath("$.gender").value("Macho"))
                .andExpect(jsonPath("$.category").value("Perro"))
                .andExpect(jsonPath("$.description").value("Lo que sea"))
                .andExpect(jsonPath("$.adopted").value(false));

    }

    @Test
    void updatePet() throws Exception {
        doNothing().when(petService).updatePetById(pet2, 2);

        ObjectMapper objectMapper = new ObjectMapper();

        String petJson = objectMapper.writeValueAsString(pet2);

        mockMvc.perform(put("/api/v1/pet/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(petJson))
                .andExpect(status().isOk());
    }

    @Test
    void getPetById() throws Exception {
        when(petService.getPetById(2)).thenReturn(Optional.of(pet2));

        mockMvc.perform(get("/api/v1/pet/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("lia"))
                .andExpect(jsonPath("$.age").value(4))
                .andExpect(jsonPath("$.breed").value("siames"))
                .andExpect(jsonPath("$.gender").value("hembra"))
                .andExpect(jsonPath("$.category").value("gato"))
                .andExpect(jsonPath("$.description").value("gato de 4 patas"))
                .andExpect(jsonPath("$.adopted").value(true));

    }

    @Test
    public void deletePetById() throws Exception {

        int petId = 2;

        when(petService.deletePetById(petId)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/pet/2", petId))
                .andExpect(status().isNoContent());

    }

    @Test
    void deletePetByIdNotfound() throws Exception{
        int petId = 2;
        when(petService.deletePetById(petId)).thenReturn(false);

        mockMvc.perform(delete("/api/v1/pet/2", petId))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletepetByIdInternalServerError() throws Exception{
        int petId = 2;
        doThrow(new RuntimeException("Unexpected Error")).when(petService).deletePetById(petId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/pet/2", petId))
                .andExpect(status().isInternalServerError());
    }
}







