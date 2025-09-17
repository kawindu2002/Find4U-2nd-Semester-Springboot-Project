////package com.find4u.find4u2ndsemesterspringbootproject.util;
////
////import jakarta.servlet.FilterChain;
////import jakarta.servlet.ServletException;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import lombok.RequiredArgsConstructor;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
////import org.springframework.stereotype.Component;
////import org.springframework.web.filter.OncePerRequestFilter;
////
////import java.io.IOException;
////
////@Component
////@RequiredArgsConstructor
////public class JwtAuthFilter extends OncePerRequestFilter {
////     private final JwtUtil jwtUtil;
////     private final UserDetailsService userDetailsService;
////
////     @Override
////     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
////                                     FilterChain filterChain) throws ServletException, IOException {
////
////          final String authHeader = request.getHeader("Authorization");
////          final String jwtToken;
////          final String username;
////
////          // Skip filter for authentication endpoints
////          if (request.getServletPath().contains("/auth/")) {
////               filterChain.doFilter(request, response);
////               return;
////          }
////
////          if (authHeader == null || !authHeader.startsWith("Bearer ")) {
////               filterChain.doFilter(request, response);
////               return;
////          }
////
////          jwtToken = authHeader.substring(7);
////
////          try {
////               username = jwtUtil.extractUsername(jwtToken);
////
////               if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
////
////                    if (jwtUtil.validateToken(jwtToken, userDetails)) {
////                         UsernamePasswordAuthenticationToken authToken =
////                              new UsernamePasswordAuthenticationToken(
////                                   userDetails,
////                                   null,
////                                   userDetails.getAuthorities());
////
////                         authToken.setDetails(
////                              new WebAuthenticationDetailsSource().buildDetails(request)
////                         );
////
////                         SecurityContextHolder.getContext().setAuthentication(authToken);
////                    }
////               }
////          } catch (Exception e) {
////               // Log the exception or handle it appropriately
////               logger.error("Cannot set user authentication: {}", e);
////          }
////
////          filterChain.doFilter(request, response);
////     }
////}
//

package com.find4u.find4u2ndsemesterspringbootproject.util;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
     private final JwtUtil jwtUtil;
     private final UserDetailsService userDetailsService;
     
     @Override
     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
          String authorization = request.getHeader("Authorization");
          
          // Check if Authorization header exists and is properly formatted
          if (authorization == null || !authorization.startsWith("Bearer ")) {
               filterChain.doFilter(request, response);
               return;
          }
          
          final String jwtToken;
          final String username;
          
          jwtToken = authorization.substring(7);
          username = jwtUtil.extractUsername(jwtToken);
          
          if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               UserDetails userDetails = userDetailsService.loadUserByUsername(username);
               
               if (!jwtUtil.validateToken(jwtToken)){
                    filterChain.doFilter(request, response);
                    return;
               }
               
               UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               
               SecurityContextHolder.getContext().setAuthentication(auth);
               
          }
          
          filterChain.doFilter(request, response);
     }
}


