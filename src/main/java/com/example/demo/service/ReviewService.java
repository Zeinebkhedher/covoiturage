package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);  // Retourne la liste des revues pour cet utilisateur
    }



    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public Double getAverageRating() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .mapToInt(Review::getRating) // Utilisation correcte de la méthode getRating()
                .average()
                .orElse(0.0);
    }
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);  // Utilise la méthode deleteById du JpaRepository
    }
    // Changer la méthode pour retourner Optional<Review>
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);  // Cette méthode retourne déjà un Optional
    }

}