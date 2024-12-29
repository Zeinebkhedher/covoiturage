package com.example.demo.controller;

import com.example.demo.model.Review;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Récupérer un avis par ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id); // Utiliser Optional<Review> ici
        if (review.isPresent()) {
            return ResponseEntity.ok(review.get()); // Utiliser review.get() pour obtenir l'objet Review
        }
        return ResponseEntity.notFound().build(); // Retourner 404 si pas trouvé
    }

    // Mettre à jour un avis
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        Optional<Review> existingReview = reviewService.getReviewById(id);
        if (existingReview.isPresent()) {
            Review reviewToUpdate = existingReview.get();

            // Mise à jour des champs de la revue
            reviewToUpdate.setComment(reviewDetails.getComment());
            reviewToUpdate.setRating(reviewDetails.getRating());
            reviewToUpdate.setUser(reviewDetails.getUser());
            reviewToUpdate.setCreatedAt(reviewDetails.getCreatedAt());

            // Sauvegarde de la revue mise à jour
            Review updatedReview = reviewService.saveReview(reviewToUpdate);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();  // Retourne 404 si la revue n'existe pas
    }

    // Supprimer un avis
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);
        if (review.isPresent()) {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();  // Retourne 204 No Content si supprimé avec succès
        }
        return ResponseEntity.notFound().build();  // Retourne 404 si la revue n'existe pas
    }

    // Récupérer les avis d'un utilisateur par ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }
}