package com.find4u.find4u2ndsemesterspringbootproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationRequestDTO {
     @NotBlank(message = "First name is required")
     @Size(max = 30)
     private String firstName;
     
     @NotBlank(message = "Last name is required")
     @Size(max = 30)
     private String lastName;
     
     @NotBlank(message = "Email is required")
     @Email(message = "Email should be valid")
     private String email;
     
     @NotBlank(message = "Phone number is required")
     private String phone;
     
     @NotBlank(message = "Password is required")
     private String password;
     
     private boolean isAgreedTermsPolicy;
     
}

