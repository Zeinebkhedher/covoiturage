package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    // Constructeur pour initialiser l'utilisateur
    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retourne les rôles de l'utilisateur. Si tu n'as pas de rôles spécifiques, tu peux en créer
        // un ici comme "ROLE_USER" ou d'autres rôles spécifiques.
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        // Retourner le mot de passe de l'utilisateur
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // Utiliser l'email comme nom d'utilisateur
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Cette méthode retourne vrai si le compte n'est pas expiré
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Cette méthode retourne vrai si le compte n'est pas verrouillé
        // Cela pourrait être utile si tu veux implémenter un mécanisme de verrouillage du compte
        return user.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Cette méthode retourne vrai si les informations d'identification (mot de passe) ne sont pas expirées
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Cette méthode retourne vrai si l'utilisateur est activé
        return user.isActive();
    }

    // Méthode pour récupérer l'utilisateur réel
    public User getUser() {
        return user;
    }
}
