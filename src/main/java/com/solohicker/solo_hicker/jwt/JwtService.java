package com.solohicker.solo_hicker.jwt;

import com.solohicker.solo_hicker.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String secretKey;

    public JwtService(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String generateJwtToken(User user){

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuer("SoloWay")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60*10*1000))
                .signWith(generateKey())
                .compact();

    }

    public SecretKey generateKey(){
        byte[] decode
                = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(decode);
    }

    public String extractUsername(String jwt) {
        return extractClaims(jwt, Claims::getSubject);
    }

    private <T> T extractClaims(String jwt, Function<Claims, T> claimresolver) {
        Claims claims = extractClaims(jwt);
        return claimresolver.apply(claims);
    }

    private Claims extractClaims(String jwt){
        return Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaims(jwt, Claims::getExpiration);
    }
}
