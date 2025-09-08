package com.find4u.find4u2ndsemesterspringbootproject.dto;

import com.find4u.find4u2ndsemesterspringbootproject.enums.Role;
import com.find4u.find4u2ndsemesterspringbootproject.enums.UserStatus;
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

