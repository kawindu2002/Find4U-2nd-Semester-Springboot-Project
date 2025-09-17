package com.find4u.find4u2ndsemesterspringbootproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User{

//   ===================================================================================================================
     
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @NotBlank
     @Column(name = "first_name")
     private String firstName;

     @NotBlank
     @Column(name = "last_name")
     private String lastName;

     @NotBlank
     @Email
     @Column(unique = true, nullable = false)
     private String email;

     @NotBlank
     @Column(name = "phone_number")
     private String phoneNumber;
     
//   ===================================================================================================================
    
     @Column(nullable = false)
     private String role = "user";;  // 'user', 'admin'
     
     @Column(nullable = false)
     private String status = "inactive";
     
     @Column(name = "is_verified", nullable = false)
     private Boolean isVerified = false;
     
     @Column(nullable = false)
     private Boolean isAgreedTermsPolicy = false;

//   ===================================================================================================================

     @NotBlank(message = "Password is required")
     private String password;
     
     @Column(name = "verification_otp")
     private String verificationOtp;
     
//   ===================================================================================================================
     
     @Column(name = "created_at",updatable = false)
     private LocalDateTime createdAt;
     
     @Column(name = "updated_at")
     private LocalDateTime updatedAt;
     
//   ===================================================================================================================
     
     @PrePersist
     protected void onCreate() {
          this.createdAt = LocalDateTime.now();
          this.updatedAt = LocalDateTime.now();
     }

     @PreUpdate
     protected void onUpdate() {
          this.updatedAt = LocalDateTime.now();
     }
     
}

// =====================================================================================================================


