package com.find4u.find4u2ndsemesterspringbootproject.util;

public class JwtFilter {
}

// ========================================== Visun =======================================================

//package lk.ijse.back_end_prerental.config;
//
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lk.ijse.back_end_prerental.service.custom.IMPL.UserServiceImpl;
//import lk.ijse.back_end_prerental.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;

//@Component
//public class JwtFilter extends OncePerRequestFilter {
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private UserServiceImpl userService;
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String authorization = httpServletRequest.getHeader("Authorization");
//        System.out.println(authorization + " 1111111");
//        String token = null;
//        String email = null;
//
//
//        if (null != authorization && authorization.startsWith("Bearer ")) {
//            System.out.println("methnin kadunaaa");
//            token = authorization.substring(7);
//            email = jwtUtil.getUsernameFromToken(token);
//            Claims claims=jwtUtil.getUserRoleCodeFromToken(token);
//            httpServletRequest.setAttribute("email", email);
//            httpServletRequest.setAttribute("role", claims.get("role"));
//        }
//
//        if (null != email && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails
//                    = userService.loadUserByUsername(email);
//
//            if (jwtUtil.validateToken(token, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//                        = new UsernamePasswordAuthenticationToken(userDetails,
//                        null, userDetails.getAuthorities());
//
//                usernamePasswordAuthenticationToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
//                );
//
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//
//    private Claims getClaimsFromJwtToken(String token) {
//        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
//    }
//
//}

// ========================================== Udara Sir =======================================================

//package org.example.o13_auth_backend.util;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthFilter extends OncePerRequestFilter {
//    private final JwtUtil jwtUtil;
//    private final UserDetailsService userDetailsService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//            FilterChain filterChain) throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//        final String jwtToken;
//        final String username;
//        if (authHeader==null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        jwtToken = authHeader.substring(7);
//        username=jwtUtil.extractUsername(jwtToken);
//        if (username!=null && SecurityContextHolder.getContext()
//                .getAuthentication()==null) {
//            UserDetails userDetails=userDetailsService
//                    .loadUserByUsername(username);
//            if (jwtUtil.validateToken(jwtToken)){
//                UsernamePasswordAuthenticationToken authToken
//                        =new UsernamePasswordAuthenticationToken(
//                                userDetails,
//                        null,
//                        userDetails.getAuthorities());
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}

//===========================================================================================================