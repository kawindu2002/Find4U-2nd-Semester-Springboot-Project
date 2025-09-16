package com.find4u.find4u2ndsemesterspringbootproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data

public class AuthRequestDTO {

     @NotBlank(message = "Email is required")
     @Email(message = "Email should be valid")
     private String email;

     @NotBlank(message = "Password is required")
     private String password;
     
}

