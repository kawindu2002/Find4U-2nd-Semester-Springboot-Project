package com.find4u.find4u2ndsemesterspringbootproject.service;

import com.kp.find4uspringbootproject.dto.UserDTO;
import com.kp.find4uspringbootproject.enums.UserStatus;

import java.util.List;

public interface UserService {

     String generateCustomId();

     void saveUser(UserDTO userDTO);

     void updateUser(UserDTO userDTO);

     boolean isExistUser(String userId);

     void deleteUser(String userId);

     List<UserDTO> getAllUsers();

     List<UserDTO> getAllByKeyword(String keyword);

     void updateUserStatusById(String id, UserStatus newStatus);

}

