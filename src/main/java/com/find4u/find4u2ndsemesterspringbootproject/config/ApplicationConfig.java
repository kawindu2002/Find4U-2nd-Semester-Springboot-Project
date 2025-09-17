
package com.find4u.find4u2ndsemesterspringbootproject.config;

import com.find4u.find4u2ndsemesterspringbootproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
     private final UserRepository userRepository;
     
     @Bean
     public UserDetailsService userDetailsService() {
          return username -> userRepository.findByEmail(username)
               .map(user -> new
                    org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(
                         "ROLE_"+user.getRole()))
               
               )).orElseThrow(
                    ()->new RuntimeException("User not found")
               );
     }
     
     @Bean
     public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }
}

