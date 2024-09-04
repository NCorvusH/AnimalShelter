package com.AnimalShelter.repository;

import com.AnimalShelter.model.AdoptionApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Integer> {
}