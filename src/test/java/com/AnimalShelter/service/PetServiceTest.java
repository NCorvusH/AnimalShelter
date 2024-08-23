package com.AnimalShelter.service;

import com.AnimalShelter.repository.IPetRepository;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PetServiceTest {

    @Mock
    private IPetRepository iPetRepository;

}
