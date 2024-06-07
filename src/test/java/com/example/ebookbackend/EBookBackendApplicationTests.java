package com.example.ebookbackend;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@SpringBootTest
class EBookBackendApplicationTests {

    @Test
    void contextLoads() {
    }

    // generate JWT
    @Test
    public void testGenJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "userKangkang-ebookstore")
                .setClaims(claims) // 有效期1h
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("userKangkang-ebookstore")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxNTM1MTM3M30.c1X5V5U4IABCXcmPfGITt5Ck01YTHQjsnQqqq8bSbXA")
                .getBody();
        System.out.println(claims);
    }

}
