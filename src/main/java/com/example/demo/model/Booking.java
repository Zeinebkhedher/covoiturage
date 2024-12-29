package com.example.demo.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // L'utilisateur qui a fait la réservation

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;  // La course pour laquelle la réservation a été faite

    @Column(nullable = false)
    private LocalDateTime bookingDate;  // La date et l'heure de la réservation

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;  // Le statut de la réservation (par exemple, PENDING, CONFIRMED, CANCELLED)

    // Constructeur par défaut
    public Booking() {
    }

    // Constructeur avec tous les paramètres
    public Booking(User user, Ride ride, LocalDateTime bookingDate, BookingStatus status) {
        this.user = user;
        this.ride = ride;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user.getLastName() +  // Utilisation de getUsername de User
                ", ride=" + ride.getTitle() +  // Utilisation de getTitle de Ride
                ", bookingDate=" + bookingDate +
                ", status=" + status +
                '}';
    }
    @PrePersist
    public void validateBooking() {
        if (this.bookingDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La réservation ne peut pas être dans le passé.");
        }
        if (this.user == null || this.ride == null) {
            throw new IllegalArgumentException("L'utilisateur et la course sont obligatoires.");
        }
    }
}