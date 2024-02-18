package com.msig.school.backend.util;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil implements Serializable {
    @Value("${jwt.secret}")
    private static String secret;
    @Value("${jwt.expiration}")
    private static Long expiration;

    private static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public static String generateToken(UserDto user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .id(user.getEmail())
                .issuedAt(convertToDate(LocalDateTime.now()))
                .expiration(convertToDate(tokenExpiration()))
                .signWith(SignatureAlgorithm.HS512, getSigningKey()).compact();
    }

    public static Date convertToDate(LocalDateTime dateTime) {
        return Date.from(dateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime tokenExpiration() {
        return LocalDateTime.now().plusMinutes(expiration);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Date getIssuedDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UserDto user) {
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }
}