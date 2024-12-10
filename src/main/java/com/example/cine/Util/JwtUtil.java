package com.example.cine.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final String key = "mi_clave";

    public String generateToken(UserDetails userDetails) {
        // Algoritmo de firma
        Algorithm algorithm = Algorithm.HMAC256(key);

        // Obtener las autoridades del usuario
        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Generar el token
        int EXPIRATION_TIME = 10 * 60 * 60 * 1000;
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("authorities", authorities)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    // Obtener el nombre de usuario del token
    public  String getUsernameFromToken(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    // Obtener las autoridades del token
    public  String getAuthoritiesFromToken(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("authorities").asString();
    }

    // Validar el token
    public  DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            return verifier.verify(token);

        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token invalido, no Autorizado");
        }
    }

    // Verificar si el token ha expirado
    public  boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getExpiresAt().before(new Date());
        } catch (JWTDecodeException e) {
            // Error al decodificar el token
            return true;
        }
    }

}
