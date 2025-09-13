package com.find4u.find4u2ndsemesterspringbootproject.service;

import com.find4u.find4u2ndsemesterspringbootproject.dto.UserDTO;
import com.find4u.find4u2ndsemesterspringbootproject.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
     
     void saveUser(UserDTO userDTO);
     
     void updateUser(UserDTO userDTO);

     boolean isExistUser(Long userId);

     void deleteUser(Long userId);

     List<UserDTO> getAllUsers();
     
     void updateUserStatusById(Long userId, String newStatus);
     
     void registerUser(UserDTO userDTO);
     
     Optional<User> findByEmail(String email);
     
     String generateVerificationOtp();
     
//     public boolean verifyUser(String otp);
     boolean verifyUser(String email,String otp);
}


