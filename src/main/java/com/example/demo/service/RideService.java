package com.example.demo.service;

import com.example.demo.model.Ride;
import com.example.demo.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    @Autowired
    private RideRepository RideRepository;

    // Récupérer toutes les courses
    public List<Ride> getAllRides() {
        return RideRepository.findAll();
    }

    // Récupérer une course par son ID
    public Ride getRideById(Long id) {
        Optional<Ride> ride = RideRepository.findById(id);
        return ride.orElseThrow(() -> new RuntimeException("Ride not found with id " + id));
    }

    // Sauvegarder une nouvelle course ou mettre à jour une course existante
    public Ride saveRide(Ride ride) {
        // Vous pouvez ajouter ici une logique de validation ou d'autres opérations avant d'enregistrer la course
        return RideRepository.save(ride);
    }

    // Récupérer les courses par destination
    public List<Ride> getRidesByDestination(String destination) {
        return RideRepository.findByDestinationLocation(destination);
    }

    // Récupérer les courses actives (en fonction du champ "active")
    public List<Ride> getActiveRides() {
        return RideRepository.findByActive(true);
    }

    // Récupérer les courses à une heure précise
    public List<Ride> getRidesByDepartureTime(LocalDateTime time) {
        return RideRepository.findByDepartureTime(time);
    }

    // Mettre à jour le statut "active" d'une course
    public Ride updateRideActiveStatus(Long id, Boolean active) {
        Ride ride = getRideById(id); // Récupérer la course
        ride.setActive(active); // Modifier le statut
        return RideRepository.save(ride); // Sauvegarder les modifications
    }
}