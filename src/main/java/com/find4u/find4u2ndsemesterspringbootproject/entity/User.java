package com.find4u.find4u2ndsemesterspringbootproject.entity;

import com.find4u.find4u2ndsemesterspringbootproject.enums.Role;
import com.find4u.find4u2ndsemesterspringbootproject.enums.UserStatus;
import com.find4u.find4u2ndsemesterspringbootproject.enums.YesNo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.UUID;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {

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
     
// =====================================================================================================================

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


//package com.find4u.model;
//
//import javax.persistence.*;
//     import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Entity
//@Table(name = "users")
//public class User {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//
//     @NotBlank(message = "First name is required")
//     @Size(max = 50)
//     @Column(name = "first_name")
//     private String firstName;
//
//     @NotBlank(message = "Last name is required")
//     @Size(max = 50)
//     @Column(name = "last_name")
//     private String lastName;
//
//     @NotBlank(message = "Email is required")
//     @Email(message = "Email should be valid")
//     @Column(unique = true)
//     private String email;
//
//     @NotBlank(message = "Phone number is required")
//     @Size(max = 15)
//     private String phone;
//
//     @NotBlank(message = "Password is required")
//     @Size(min = 8, message = "Password must be at least 8 characters")
//     private String password;
//
//     @Column(name = "verification_token")
//     private String verificationToken;
//
//     @Column(name = "is_verified")
//     private boolean isVerified = false;
//
//     @Column(name = "created_at")
//     private LocalDateTime createdAt;
//
//     @Column(name = "updated_at")
//     private LocalDateTime updatedAt;
//
//     // Constructors
//     public User() {
//     }
//
//     public User(String firstName, String lastName, String email, String phone, String password) {
//          this.firstName = firstName;
//          this.lastName = lastName;
//          this.email = email;
//          this.phone = phone;
//          this.password = password;
//     }
//
//     @PrePersist
//     protected void onCreate() {
//          this.createdAt = LocalDateTime.now();
//          this.updatedAt = LocalDateTime.now();
//          this.verificationToken = UUID.randomUUID().toString();
//     }
//
//     @PreUpdate
//     protected void onUpdate() {
//          this.updatedAt = LocalDateTime.now();
//     }
//
//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
//
//     public String getFirstName() { return firstName; }
//     public void setFirstName(String firstName) { this.firstName = firstName; }
//
//     public String getLastName() { return lastName; }
//     public void setLastName(String lastName) { this.lastName = lastName; }
//
//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }
//
//     public String getPhone() { return phone; }
//     public void setPhone(String phone) { this.phone = phone; }
//
//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }
//
//     public String getVerificationToken() { return verificationToken; }
//     public void setVerificationToken(String verificationToken) { this.verificationToken = verificationToken; }
//
//     public boolean isVerified() { return isVerified; }
//     public void setVerified(boolean verified) { isVerified = verified; }
//
//     public LocalDateTime getCreatedAt() { return createdAt; }
//     public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
//
//     public LocalDateTime getUpdatedAt() { return updatedAt; }
//     public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
//}
//

// =====================================================================================================================


//     @Id
//     private String id; // e.g., USR-2025-00001
//
//     private String name;
//
//     @Column(unique = true)
//     private String email;
//
//     @Column(name = "phone_number")
//     private String phoneNumber;
//
//     @Enumerated(EnumType.STRING)
//     private Role role = Role.USER;;  // 'user', 'admin'
//
//     @Enumerated(EnumType.STRING)
//     private UserStatus status = UserStatus.ACTIVE;   // 'active', 'inactive'
//
//     @Column(name = "last_login")
//     private LocalDateTime lastLogin;
//
//     @CreationTimestamp
//     @Column(name = "created_at",updatable = false)
//     private LocalDateTime createdAt;
//
//     @UpdateTimestamp
//     @Column(name = "updated_at")
//     private LocalDateTime updatedAt;