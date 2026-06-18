package com.roomdekho.jwt;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;


@Service
public class JwtService {


    private final String SECRET_KEY =
            "roomdekho_room_booking_application_secret_key_2026";


    public String generateToken(String email){

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis()+1000*60*60*24*7)
                )
                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes()
                        ),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }


    public String extractEmail(String token){

        return Jwts.parserBuilder()
                .setSigningKey(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes()
                        )
                )
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}