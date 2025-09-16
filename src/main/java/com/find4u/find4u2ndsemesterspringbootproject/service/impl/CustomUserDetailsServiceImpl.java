package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import com.find4u.find4u2ndsemesterspringbootproject.exception.AccountNotVerifiedException;
import lombok.RequiredArgsConstructor;
import com.find4u.find4u2ndsemesterspringbootproject.entity.User;
import com.find4u.find4u2ndsemesterspringbootproject.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
     private final UserRepository userRepository;
     
     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          Optional<User> userOptional = userRepository.findByEmail(email);

          if (userOptional.isPresent()) {
               User user = userOptional.get();

               System.out.println("status : "+user.getStatus()+","+"is verified : "+user.getIsVerified());

               
               if (!user.getIsVerified()) {
                    throw new AccountNotVerifiedException("User account has not been verified.");
               }
               
               if (!"active".equals(user.getStatus())) {
                    throw new UsernameNotFoundException("User account is inactive.");
               }
               
               
               return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
               );
          }
          throw new UsernameNotFoundException("User not found with email: " + email);
     }
     
}

