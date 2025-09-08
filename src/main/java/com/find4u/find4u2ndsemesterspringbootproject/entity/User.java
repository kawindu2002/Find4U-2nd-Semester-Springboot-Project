package com.find4u.find4u2ndsemesterspringbootproject.entity;

import com.find4u.find4u2ndsemesterspringbootproject.enums.Role;
import com.find4u.find4u2ndsemesterspringbootproject.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
     @Id
     private String id; // e.g., USR-2025-00001
     
     private String name;
     
     @Column(unique = true)
     private String email;
     
     @Column(name = "phone_number")
     private String phoneNumber;
     
     @Enumerated(EnumType.STRING)
     private Role role = Role.USER;;  // 'user', 'admin'
     
     @Enumerated(EnumType.STRING)
     private UserStatus status = UserStatus.ACTIVE;   // 'active', 'inactive'
     
     @Column(name = "last_login")
     private LocalDateTime lastLogin;
     
     @CreationTimestamp
     @Column(name = "created_at",updatable = false)
     private LocalDateTime createdAt;
     
     @UpdateTimestamp
     @Column(name = "updated_at")
     private LocalDateTime updatedAt;
     
}

