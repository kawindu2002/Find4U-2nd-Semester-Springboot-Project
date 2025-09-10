package com.find4u.find4u2ndsemesterspringbootproject.dto;

import com.find4u.find4u2ndsemesterspringbootproject.enums.Role;
import com.find4u.find4u2ndsemesterspringbootproject.enums.UserStatus;
import com.find4u.find4u2ndsemesterspringbootproject.enums.YesNo;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
     
     private Role role;  // 'user', 'admin'
     private UserStatus status; // 'active', 'inactive'
     private YesNo isVerified; // 'Yes', 'No'
     private YesNo isAgreedTermsPolicy;

//   ===================================================================================================================
     
     @NotBlank(message = "Password is required")
     private String password;
     private String verificationToken;

//   ===================================================================================================================
     
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;

//   ===================================================================================================================

}

