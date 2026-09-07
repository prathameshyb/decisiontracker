package com.prathamesh.decisiontracker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.util.Base64;


@Service
public class JwtService {

    private final String encodedSecretKey = System.getenv("JWTSecretKeyEncoded");
    byte[] decodedByte= Base64.getDecoder().decode(encodedSecretKey);
    SecretKey secretKey=  new SecretKeySpec(decodedByte, "HmacSHA256");


    public String getJwtToken (String username){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + 3600000);

        String jwtToken = Jwts.builder()
                .header().type("JWT")
                .and()
                .subject(username)
                .issuedAt(now)
                .expiration(exp)
                .signWith(secretKey)
                .compact();

        return jwtToken;
    }
    public Claims getPayload(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody(); // Returns the payload claims
    }

    public void extractData(String token) {
        Claims claims = getPayload(token);
        String username = claims.getSubject();
    }


}
