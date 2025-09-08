package com.find4u.find4u2ndsemesterspringbootproject.dto;

import com.kp.find4uspringbootproject.enums.Role;
import com.kp.find4uspringbootproject.enums.UserStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDTO {
     private String id;  // e.g., USR-2025-00001
     private String name;
     private String email;
     private String phoneNumber;
     private Role role;     // 'user', 'admin'
     private UserStatus status;   // 'active', 'inactive'
     private LocalDateTime lastLogin;
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;
     
}

