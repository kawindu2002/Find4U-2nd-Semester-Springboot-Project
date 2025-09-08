package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import com.find4u.find4u2ndsemesterspringbootproject.dto.UserDTO;
import com.find4u.find4u2ndsemesterspringbootproject.entity.User;
import com.find4u.find4u2ndsemesterspringbootproject.enums.UserStatus;
import com.find4u.find4u2ndsemesterspringbootproject.exception.NotFoundException;
import com.find4u.find4u2ndsemesterspringbootproject.repository.UserRepository;
import com.find4u.find4u2ndsemesterspringbootproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
     private final UserRepository userRepo;
     private final ModelMapper modelMapper;
     
     @Override
     public String generateCustomId() {
          String prefix = "USR";
          String year = String.valueOf(LocalDate.now().getYear()); // USR-2025-%
          String likePattern = prefix + "-" + year + "-";
          // Count how many already exist with this year
          long count = userRepo.countByIdStartingWith(likePattern);
          long next = count + 1; // Get the next number in line
          String sequence = String.format("%05d", next); // Pad the number to 5 digits: 00001, 00002, etc.
          return String.format("%s-%s-%s", prefix, year, sequence); // Final ID: USR-2025-00001
     }
     
     @Override
     public void saveUser(UserDTO userDTO) {
        String customId = generateCustomId();
        userDTO.setId(customId);
        User user = modelMapper.map(userDTO, User.class);
          userRepo.save(user);
     }
     
     @Override
     public void updateUser(UserDTO userDTO) {
          userRepo.findById(userDTO.getId())
               .orElseThrow(() -> new NotFoundException("User not found with ID: " + userDTO.getId()));
          
          User user = modelMapper.map(userDTO, User.class);
          userRepo.save(user);
     }
     
     @Override
     public boolean isExistUser(String userId) {
          return userRepo.existsById(userId);
     }

     @Override
     public void deleteUser(String userId) {
          userRepo.findById(userId)
               .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
          
          userRepo.deleteById(userId);
     }
     
     @Override
     public List<UserDTO> getAllUsers() {
          List<User> allUsers = userRepo.findAll();
          return modelMapper.map(allUsers, new TypeToken<List<UserDTO>>() {}.getType());
     }

     @Override
     public List<UserDTO> getAllByKeyword(String keyword) {
          List<User> list = userRepo.findUserByIdContainingIgnoreCase(keyword);
          return modelMapper.map(list, new TypeToken<List<UserDTO>>(){}.getType());
     }

     @Override
     public void updateUserStatusById(String userId, UserStatus newStatus) {
          userRepo.findById(userId)
               .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
          userRepo.updateUserStatusById(userId, newStatus);
     }
     
}

