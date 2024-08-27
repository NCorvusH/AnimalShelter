package com.AnimalShelter.repository;

import com.AnimalShelter.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPetRepository extends JpaRepository<Pet, Integer> {
}