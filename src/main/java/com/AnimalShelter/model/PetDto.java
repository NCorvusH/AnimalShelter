package com.AnimalShelter.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class PetDto {

    private int id;
    private String name;
    private int age;
    private String breed;
    private String gender;
    private Boolean sex;
    private String category;
    private String description;
    private boolean adopted;
    private MultipartFile photo;
}

