package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.demo.model.CustomUserDetails;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {

    private static final String SECRET_KEY = "MaCleSecreteSuperSecrete123!";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours
    private final Key key;

    public JwtUtil() {
        key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Extraire un claim spécifique à partir du token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token); // Récupère tous les claims
        return claimsResolver.apply(claims); // Applique la fonction pour extraire un claim spécifique
    }

    // Récupérer tous les claims du token
    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key) // Utilise la clé secrète pour valider le token
                .build()
                .parseSignedClaims(token)// Parse le JWT et récupère le corps du JWT
                .getBody(); // Récupère le corps du JWT contenant tous les claims
    }


    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claimsResolver.apply(claims);
    }

    public String generateToken(CustomUserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
