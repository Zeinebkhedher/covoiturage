package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Chercher l'utilisateur dans la base de données par email
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username));

        // Convertir les rôles de l'utilisateur en autorités Spring Security
        //List<GrantedAuthority> authorities = user.getRoles().stream()
                //.map(role -> new SimpleGrantedAuthority(role.getRoleName())) // Récupérer le nom du rôle
                //.collect(Collectors.toList());

        // Retourner un objet UserDetails en utilisant les données de l'utilisateur récupérées
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail()) // Utilise l'email comme nom d'utilisateur
                .password(user.getPassword()) // Mot de passe de l'utilisateur
                //.authorities(authorities) // Liste des rôles/authorities
                .accountExpired(false) // Indique si le compte est expiré
                .accountLocked(false) // Indique si le compte est verrouillé
                .credentialsExpired(false) // Indique si les identifiants sont expirés
                .disabled(!user.isActive()) // Indique si le compte est désactivé
                .build();
    }
}
