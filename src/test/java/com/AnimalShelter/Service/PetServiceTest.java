package com.AnimalShelter.Service;

import com.AnimalShelter.Model.Pet;
import com.AnimalShelter.Repository.IPetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
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
    }
}
