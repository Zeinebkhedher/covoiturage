package com.example.demo.repository;
import com.example.demo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Trouver des voitures par propriétaire
    List<Car> findByOwnerId(Long ownerId);

    // Trouver des voitures par marque
    List<Car> findByBrand(String brand);

    // Trouver des voitures par modèle
    List<Car> findByModel(String model);
}
