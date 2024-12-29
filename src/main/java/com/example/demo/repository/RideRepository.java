package com.example.demo.repository;
import com.example.demo.model.Ride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

// Interface repository pour les trajets (Ride)
@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByDestinationLocation(String destinationLocation);

    // Méthode pour récupérer les courses actives
    List<Ride> findByActive(Boolean active);

    // Méthode pour récupérer les courses par heure de départ
    List<Ride> findByDepartureTime(LocalDateTime departureTime);

    // Requête JPQL pour trouver les courses par titre
    @Query("SELECT r FROM Ride r WHERE r.title = :title")
    List<Ride> findByTitle(@Param("title") String title);

    // Requête JPQL pour trouver les courses actives créées après une certaine date
    @Query("SELECT r FROM Ride r WHERE r.active = true AND r.createdAt > :date")
    List<Ride> findActiveRidesAfterDate(@Param("date") LocalDateTime date);

    // Requête JPQL pour trouver les courses entre deux lieux
    @Query("SELECT r FROM Ride r WHERE r.departureLocation = :departure AND r.destinationLocation = :destination")
    List<Ride> findByRoute(@Param("departure") String departure, @Param("destination") String destination);

    // Requête JPQL pour trouver les courses avec des places disponibles triées par date
    @Query("SELECT r FROM Ride r WHERE r.availableSeats > 0 ORDER BY r.departureTime ASC")
    List<Ride> findAvailableRidesSortedByDate();



}