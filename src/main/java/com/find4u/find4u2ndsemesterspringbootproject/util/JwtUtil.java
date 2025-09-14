package com.find4u.find4u2ndsemesterspringbootproject.util;

public class JwtUtil {
}

// ========================================== Visun =======================================================

//
//package lk.ijse.back_end_prerental.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lk.ijse.back_end_prerental.dto.PaymentDTO;
//import lk.ijse.back_end_prerental.dto.PaymentDTO2;
//import lk.ijse.back_end_prerental.dto.UserDTO;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//
//@Component
//@PropertySource(ignoreResourceNotFound = true, value = "classpath:otherprops.properties")
//public class JwtUtil implements Serializable {
//
//     private static final long serialVersionUID = 234234523523L;
//
//     public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 12;
//
//     @Value("${jwt.secret}")
//     private String secretKey;
//
//     //retrieve username from jwt token
//     public String getUsernameFromToken(String token) {
//          return getClaimFromToken(token, Claims::getSubject);
//     }
//
//     public Claims getUserRoleCodeFromToken(String token) {
//          return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//     }
//
//     //retrieve expiration date from jwt token
//     public Date getExpirationDateFromToken(String token) {
//          return getClaimFromToken(token, Claims::getExpiration);
//     }
//
//
//     public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//          final Claims claims = getAllClaimsFromToken(token);
//          return claimsResolver.apply(claims);
//     }
//
//
//     //for retrieving any information from token we will need the secret key
//     private Claims getAllClaimsFromToken(String token) {
//          return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//     }
//
//
//     //check if the token has expired
//     private Boolean isTokenExpired(String token) {
//          final Date expiration = getExpirationDateFromToken(token);
//          return expiration.before(new Date());
//     }
//
     //generate token for user
//     public String generateToken(UserDTO userDTO) {
//          Map<String, Object> claims = new HashMap<>();
//          claims.put("email",userDTO.getEmail());
//          return doGenerateToken(claims, userDTO.getEmail());
//     }
//
//     public String generateTokenPay(PaymentDTO2 paymentDTO) {
//          Map<String, Object> claims = new HashMap<>();
//          claims.put("cardNumber",paymentDTO.getCardNumber());
//          return doGenerateToken(claims, paymentDTO.getCardNumber());
//     }
     
     //while creating the token -
     //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
     //2. Sign the JWT using the HS512 algorithm and secret key.
//     private String doGenerateToken(Map<String, Object> claims, String subject) {
//          return Jwts.builder()
//               .setClaims(claims)
//               .setSubject(subject)
//               .setIssuedAt(new Date(System.currentTimeMillis()))
//               .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//               .signWith(SignatureAlgorithm.HS512, secretKey).compact();
//     }
//
//     //validate token
//     public Boolean validateToken(String token, UserDetails userDetails) {
//          final String username = getUsernameFromToken(token);
//          return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//     }
//
//}

// ========================================== Udara Sir =======================================================

//package org.example.o13_auth_backend.util;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//    @Value("${jwt.expiration}")
//    private long expiration;
//
//    @Value("${jwt.secretKey}")
//    private String secretKey;
//
//    public String generateToken(String username){
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(
//                        System.currentTimeMillis() + expiration))
//                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes())
//                ,SignatureAlgorithm.HS256).compact();
//    }
//    public String extractUsername(String token){
//        return Jwts.parserBuilder()
//                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//    public boolean validateToken(String token){
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
//}

//==========================================================================================================