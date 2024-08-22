package com.AnimalShelter.Service;

import com.AnimalShelter.Repository.IPetRepository;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PetServiceTest {

    @Mock
    private IPetRepository iPetRepository;

}
