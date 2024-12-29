package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // Récupérer toutes les réservations
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Récupérer une réservation par ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // Créer une nouvelle réservation
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Supprimer une réservation
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}