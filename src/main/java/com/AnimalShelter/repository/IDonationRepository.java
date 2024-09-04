package com.AnimalShelter.repository;

import com.AnimalShelter.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDonationRepository extends JpaRepository<Donation, Integer> {

}