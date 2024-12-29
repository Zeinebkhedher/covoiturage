package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Recherche par email (basé sur conventions Spring Data JPA)
    Optional<User> findByEmail(String email);

    // Recherche par nom de famille
    Optional<User> findByLastName(String lastName);

    // Recherche par prénom
    Optional<User> findByFirstName(String firstName);

    // Recherche par prénom et nom
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    // Recherche par email ou prénom
    List<User> findByEmailOrFirstName(String email, String firstName);

    // Recherche par nom avec pagination
    Page<User> findByLastName(String lastName, Pageable pageable);

    // Recherche par prénom avec tri
    List<User> findByFirstName(String firstName, Sort sort);

    // Requête personnalisée : recherche par domaine d'email
    @Query("SELECT u FROM User u WHERE u.email LIKE %:emailDomain%")
    List<User> findByEmailDomain(@Param("emailDomain") String emailDomain);

    // Requête personnalisée : recherche des utilisateurs actifs par nom de famille
    @Query("SELECT u FROM User u WHERE u.lastName = :lastName AND u.active = true")
    List<User> findActiveUsersByLastName(@Param("lastName") String lastName);

    // Requête personnalisée : utilisateurs créés après une certaine date
    @Query("SELECT u FROM User u WHERE u.createdAt > :createdAfter")
    List<User> findUsersCreatedAfter(@Param("createdAfter") String createdAfter);

    // Requête personnalisée avec plusieurs paramètres
    @Query("SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName AND u.active = true")
    Optional<User> findActiveUserByFullName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName
    );
}
