package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // Récupérer toutes les voitures
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Récupérer une voiture par son ID
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    // Sauvegarder une nouvelle voiture
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    // Récupérer les voitures d'un propriétaire spécifique
    public List<Car> getCarsByOwner(Long ownerId) {
        return carRepository.findByOwnerId(ownerId);
    }

    // Récupérer les voitures par marque
    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    // Récupérer les voitures par modèle
    public List<Car> getCarsByModel(String model) {
        return carRepository.findByModel(model);
    }

    // Supprimer une voiture
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}