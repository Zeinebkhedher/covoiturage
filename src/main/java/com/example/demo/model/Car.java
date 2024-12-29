package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private Integer seatCapacity;

    // Constructeur sans paramètres (déjà ajouté par @NoArgsConstructor de Lombok)

    // Constructeur avec paramètres
    public Car(User owner, String brand, String model, String licensePlate, Integer seatCapacity) {
        this.owner = owner;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.seatCapacity = seatCapacity;
    }

    // Getters et Setters sont générés par Lombok grâce à l'annotation @Data
}