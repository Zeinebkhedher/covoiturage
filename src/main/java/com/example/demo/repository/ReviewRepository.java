package com.example.demo.repository;

import com.example.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Récupérer les avis d'un utilisateur en particulier
    List<Review> findAllByUserId(Long userId);
    public List<Review> findByUserId(Long userId);

}