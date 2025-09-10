package com.find4u.find4u2ndsemesterspringbootproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDTO {

//   ===================================================================================================================
     
     private Long id;
     
     @NotBlank(message = "First name is required")
     private String firstName;
     
     @NotBlank(message = "Last name is required")
     private String lastName;
     
     @NotBlank(message = "Email is required")
     @Email(message = "Email should be valid")
     private String email;
     
     @NotBlank(message = "Phone number is required")
     private String phoneNumber;

//   ===================================================================================================================
     
     private String role;  // 'user', 'admin'
     private String status; // 'active', 'inactive'
     private Boolean isVerified; // 'Yes', 'No'
     private Boolean isAgreedTermsPolicy;

//   ===================================================================================================================
     
     @NotBlank(message = "Password is required")
     private String password;
     private String verificationOTP;

//   ===================================================================================================================
     
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;

//   ===================================================================================================================

}

