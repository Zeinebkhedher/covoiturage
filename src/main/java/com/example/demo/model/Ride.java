package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le titre ne peut pas être nul")
    @Size(min = 3, max = 50, message = "Le titre doit contenir entre 3 et 50 caractères")
    private String title;  // Titre de la course

    @NotNull(message = "Le lieu de départ ne peut pas être nul")
    @Size(min = 3, max = 100, message = "Le lieu de départ doit contenir entre 3 et 100 caractères")
    private String departureLocation; // Lieu de départ

    @NotNull(message = "Le lieu de destination ne peut pas être nul")
    @Size(min = 3, max = 100, message = "Le lieu de destination doit contenir entre 3 et 100 caractères")
    private String destinationLocation; // Lieu de destination

    @NotNull(message = "L'heure de départ ne peut pas être nulle")
    private LocalDateTime departureTime; // Heure de départ

    @NotNull(message = "Le nombre de sièges disponibles ne peut pas être nul")
    @Min(value = 1, message = "Il doit y avoir au moins un siège disponible")
    private Integer availableSeats; // Nombre de sièges disponibles

    @Column(nullable = false)
    private Boolean active = true; // Indique si la course est active

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // Timestamp de création

    private LocalDateTime updatedAt; // Timestamp de mise à jour

    // Constructeur par défaut
    public Ride() {
    }

    // Constructeur avec paramètres
    public Ride(String title, String departureLocation, String destinationLocation, LocalDateTime departureTime, Integer availableSeats) {
        this.title = title;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Méthodes pour gérer les timestamps automatiquement
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // toString pour débogage
    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", departureLocation='" + departureLocation + '\'' +
                ", destinationLocation='" + destinationLocation + '\'' +
                ", departureTime=" + departureTime +
                ", availableSeats=" + availableSeats +
                ", active=" + active +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
