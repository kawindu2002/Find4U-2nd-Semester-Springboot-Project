package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import lombok.RequiredArgsConstructor;
import com.find4u.find4u2ndsemesterspringbootproject.entity.User;
import com.find4u.find4u2ndsemesterspringbootproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
     private final UserRepository userRepository;
     
     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
          
          return new org.springframework.security.core.userdetails.User(
               user.getEmail(),
               user.getPassword(),
               Collections.emptyList()
          );
     }
}
