package com.find4u.find4u2ndsemesterspringbootproject.service;

import com.find4u.find4u2ndsemesterspringbootproject.dto.UserDTO;
import com.find4u.find4u2ndsemesterspringbootproject.enums.UserStatus;

import java.util.List;

public interface UserService {
     
     void saveUser(UserDTO userDTO);

     void updateUser(UserDTO userDTO);

     boolean isExistUser(Long userId);

     void deleteUser(Long userId);

     List<UserDTO> getAllUsers();
     
     void updateUserStatusById(Long id, UserStatus newStatus);

}


