package com.find4u.find4u2ndsemesterspringbootproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
     
     private String role = "user";  // 'user', 'admin'
     private String status = "inactive"; // 'active', 'inactive'
     private Boolean isVerified = false;
     private Boolean isAgreedTermsPolicy = false;

//   ===================================================================================================================
     
     @NotBlank(message = "Password is required")
     private String password;
     private String verificationOTP;

//   ===================================================================================================================
     
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;
     
//   ===================================================================================================================
     
     public UserDTO(String firstName,String lastName, String email, String phone, String password, boolean agreedTermsPolicy) {
          this.firstName = firstName;
          this.lastName = lastName;
          this.email = email;
          this.phoneNumber = phone;
          this.password = password;
          this.isAgreedTermsPolicy = agreedTermsPolicy;
          
     }

//   ===================================================================================================================

}

