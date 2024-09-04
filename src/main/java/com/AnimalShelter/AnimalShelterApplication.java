package com.AnimalShelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
public class AnimalShelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalShelterApplication.class, args);
	}

}
