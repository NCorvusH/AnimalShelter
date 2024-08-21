package com.AnimalShelter.Repository;

import com.AnimalShelter.Model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPetRepository extends JpaRepository<Pet, Integer> {
}
