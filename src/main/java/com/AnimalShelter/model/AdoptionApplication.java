package com.AnimalShelter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "AdoptionApplication")
@NoArgsConstructor
public class AdoptionApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int petId;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate dateSubmitted;

    @Column(name = "ApplicantName")
    private String applicantName;

    @Column(nullable = false, length = 100)
    private String applicantEmail;

    @Column(nullable = false, length = 15)
    private String applicantPhone;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 255)
    private String addressLine1;

    @Column(length = 255)
    private String addressLine2;

    @Column(length = 255)
    private String addressLine3;

    @Column(nullable = false, length = 10)
    private int zipCode;

    @Column(length = 500)
    private String reasonForAdoption;

    @Column(length = 500)
    private String previousPets;

    @Column(length = 500)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;
}