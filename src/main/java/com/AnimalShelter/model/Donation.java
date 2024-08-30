package com.AnimalShelter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Donation")
@Getter
@Setter
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DonationId")
    private int donationId;

    @Column(name = "DonorName")
    private String donorName;

    @Column(name = "Message")
    private String message;

    @Column(name = "Amount")
    private BigDecimal amount;

    @Column(name = "Date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
