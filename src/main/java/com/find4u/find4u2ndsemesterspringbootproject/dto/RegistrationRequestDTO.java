package com.find4u.find4u2ndsemesterspringbootproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class RegistrationRequestDTO {
     @NotBlank(message = "First name is required")
     private String firstName;
     
     @NotBlank(message = "Last name is required")
     private String lastName;
     
     @NotBlank(message = "Email is required")
     @Email(message = "Email should be valid")
     private String email;
     
     @NotBlank(message = "Phone number is required")
     private String phone;
     
     @NotBlank(message = "Password is required")
     private String password;
     
     private boolean isAgreedTermsPolicy = false;
     
}

