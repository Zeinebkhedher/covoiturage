package com.example.demo.repository;

import com.example.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Trouver une réservation par son ID
    Optional<Booking> findById(Long id);

    // Trouver une réservation par l'ID de l'utilisateur et l'ID de la course
    List<Booking> findByUser_IdAndRide_Id(Long userId, Long rideId);

    // Trouver toutes les réservations d'un utilisateur spécifique
    List<Booking> findByUser_Id(Long userId);

    // Trouver toutes les réservations d'une course spécifique
    List<Booking> findByRide_Id(Long rideId);

    // Trouver toutes les réservations avec un statut spécifique
    List<Booking> findByStatus(String status);

    // Trouver toutes les réservations d'un utilisateur avec un statut spécifique
    List<Booking> findByUser_IdAndStatus(Long userId, String status);

    // Trouver toutes les réservations d'une course avec un statut spécifique
    List<Booking> findByRide_IdAndStatus(Long rideId, String status);

    // Trouver la dernière réservation pour un utilisateur donné
    Optional<Booking> findTopByUser_IdOrderByIdDesc(Long userId);

    // Vérifier si une réservation existe pour un utilisateur et une course donnés
    boolean existsByUser_IdAndRide_Id(Long userId, Long rideId);

    // Supprimer toutes les réservations pour un utilisateur spécifique
    void deleteByUser_Id(Long userId);

    // Supprimer toutes les réservations pour une course spécifique
    void deleteByRide_Id(Long rideId);

}