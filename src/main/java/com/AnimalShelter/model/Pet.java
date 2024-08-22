package com.AnimalShelter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pet")
@Getter
@Setter
@NoArgsConstructor
public class Pet {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Age")
    private int age;

    @Column(name ="Breed")
    private String breed;

    @Column(name= "Gender")
    private String gender;
}
