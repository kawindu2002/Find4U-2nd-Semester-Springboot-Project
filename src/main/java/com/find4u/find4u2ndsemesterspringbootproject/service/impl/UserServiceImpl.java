package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import com.find4u.find4u2ndsemesterspringbootproject.dto.UserDTO;
import com.find4u.find4u2ndsemesterspringbootproject.entity.User;
import com.find4u.find4u2ndsemesterspringbootproject.exception.NotFoundException;
import com.find4u.find4u2ndsemesterspringbootproject.repository.UserRepository;
import com.find4u.find4u2ndsemesterspringbootproject.service.EmailService;
import com.find4u.find4u2ndsemesterspringbootproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
     private final UserRepository userRepo;
     private final ModelMapper modelMapper;
   private final PasswordEncoder passwordEncoder;
     private final EmailService emailService;
     
//   ===================================================================================================================
     
     @Override
     public void saveUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepo.save(user);
     }
     
//   ===================================================================================================================
     
     @Override
     public void updateUser(UserDTO userDTO) {
          userRepo.findById(userDTO.getId())
               .orElseThrow(() -> new NotFoundException("User not found with ID: " + userDTO.getId()));
          
          saveUser(userDTO);
     }
     
//   ===================================================================================================================
     
     @Override
     public boolean isExistUser(Long userId) {
          return userRepo.existsById(userId);
     }

//   ===================================================================================================================
     
     @Override
     public void deleteUser(Long userId) {
          userRepo.findById(userId)
               .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
          userRepo.deleteById(userId);
     }

//   ===================================================================================================================

     @Override
     public List<UserDTO> getAllUsers() {
          List<User> allUsers = userRepo.findAll();
          return modelMapper.map(allUsers, new TypeToken<List<UserDTO>>() {}.getType());
     }
     
//   ===================================================================================================================

     @Override
     public void updateUserStatusById(Long userId, String newStatus) {
          // Check if id already exists
          userRepo.findById(userId)
               .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
          
          // If exists, update the use status
          userRepo.updateUserStatusById(userId, newStatus);
     }

//   ===================================================================================================================
     
     @Override
     public void registerUser(UserDTO userDTO) {
        
        // Check if email already exists
        if (userRepo.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        
        // Encrypt password
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
     
        // Save user
        saveUser(userDTO);
     
        // Send verification email with otp
        emailService.sendVerificationOtp(userDTO.getEmail(), userDTO.getVerificationOTP());
     
     }

//   ===================================================================================================================
     
     @Override
     public boolean verifyUser(String email,String otp) {
        
        Optional<User> userOptional = findByEmail(email);
          
          if (userOptional.isPresent()) {
            User user = userOptional.get();
             if(user.getVerificationOtp().equals(otp)) {
                  user.setIsVerified(true);
                  user.setStatus("active");
                  // Clear the otp after verification
                  user.setVerificationOtp(null);
                  userRepo.save(user);
                  return true;
             }
        }
        return false;
     }
     
//   ===================================================================================================================
     
     @Override
     public Optional<User> findByEmail(String email) {
          return userRepo.findByEmail(email);
     }
     
//   ===================================================================================================================
     
     public String generateVerificationOtp() {
          SecureRandom random = new SecureRandom();
          int otpValue = random.nextInt(1000000);
          return String.format("%06d", otpValue);
     }
     
//   ===================================================================================================================

}


