package com.find4u.find4u2ndsemesterspringbootproject.config;

import lombok.RequiredArgsConstructor;
import com.find4u.find4u2ndsemesterspringbootproject.util.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor

public class SecurityConfig {
     private final UserDetailsService userDetailsService;
     private final PasswordEncoder passwordEncoder;
     private final JwtAuthFilter jwtAuthFilter;
     
     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          http
               .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(
                    auth->
                         auth.requestMatchers(
                                   "/api/v1/find4u/auth/register",
                                   "/api/v1/find4u/auth/login",
                                   "/api/v1/find4u/auth/verify",
                                   "/api/v1/find4u/auth/forgot-password",
                                   "/api/v1/find4u/auth/reset-password",
                                   "/swagger-ui/**",      // If using Swagger
                                   "/v3/api-docs/**",     // If using Swagger
                                   "/public/**"           // General public endpoints
                              ).permitAll()
                              .anyRequest().authenticated())
               .sessionManagement(
                    session->session
                         .sessionCreationPolicy(
                              SessionCreationPolicy.STATELESS)
               )
               .authenticationProvider(authenticationProvider())
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
          return http.build();
     }
     
     @Bean
     public CorsConfigurationSource corsConfigurationSource() {
          CorsConfiguration configuration = new CorsConfiguration();
          configuration.setAllowedOrigins(Arrays.asList(
               "http://localhost",
               "http://127.0.0.1",
               "http://localhost:63342",
               "http://localhost:5500",  // For Live Server extension
               "http://127.0.0.1:5500",  // For Live Server extension
               "http://localhost:3000",  // For React development
               "http://127.0.0.1:3000"   // For React development
          ));
          configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
          configuration.setAllowedHeaders(Arrays.asList("*"));
          configuration.setAllowCredentials(true);
          configuration.setMaxAge(3600L); // 1 hour
          
          UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
          source.registerCorsConfiguration("/**", configuration);
          return source;
     }
     
     
     @Bean
     public AuthenticationProvider authenticationProvider() {
          DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
          provider.setUserDetailsService(userDetailsService);
          provider.setPasswordEncoder(passwordEncoder);
          return provider;
     }
     
}

