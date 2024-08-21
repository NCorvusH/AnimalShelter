package com.AnimalShelter.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Donation")
@Getter
@Setter
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(name ="Quantity")
    private String quantity;

    @Column(name = "Reason")
    private String reason;

    @Column(name = "Donator")
    private String donator;
}
