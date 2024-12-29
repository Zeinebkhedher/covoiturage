package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // Récupérer toutes les voitures
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    // Récupérer une voiture par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carService.getCarById(id);
        return car.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Sauvegarder une nouvelle voiture
    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car) {
        Car savedCar = carService.saveCar(car);
        return ResponseEntity.ok(savedCar);
    }

    // Récupérer les voitures d'un propriétaire spécifique
    @GetMapping("/owner/{ownerId}")
    public List<Car> getCarsByOwner(@PathVariable Long ownerId) {
        return carService.getCarsByOwner(ownerId);
    }

    // Récupérer les voitures par marque
    @GetMapping("/brand/{brand}")
    public List<Car> getCarsByBrand(@PathVariable String brand) {
        return carService.getCarsByBrand(brand);
    }

    // Récupérer les voitures par modèle
    @GetMapping("/model/{model}")
    public List<Car> getCarsByModel(@PathVariable String model) {
        return carService.getCarsByModel(model);
    }

    // Supprimer une voiture
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}