package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import lombok.RequiredArgsConstructor;
import com.find4u.find4u2ndsemesterspringbootproject.entity.User;
import com.find4u.find4u2ndsemesterspringbootproject.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
     private final UserRepository userRepository;
     
     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
          
          // Check if user is verified and active
          if (!user.getIsVerified() || !"active".equals(user.getStatus())) {
               throw new UsernameNotFoundException("User account is not verified or inactive");
          }
          
          return new org.springframework.security.core.userdetails.User(
               user.getEmail(),
               user.getPassword(),
               Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
          );
     }
}

