package com.find4u.find4u2ndsemesterspringbootproject.controller;

import com.find4u.find4u2ndsemesterspringbootproject.dto.APIResponse;
import com.find4u.find4u2ndsemesterspringbootproject.dto.RegistrationRequestDTO;
import com.find4u.find4u2ndsemesterspringbootproject.dto.UserDTO;
import com.find4u.find4u2ndsemesterspringbootproject.entity.User;
import com.find4u.find4u2ndsemesterspringbootproject.enums.UserStatus;
import com.find4u.find4u2ndsemesterspringbootproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/find4u/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final UserService userService;
    
//    @PostMapping("/register")
//    public ResponseEntity<APIResponse> registerUser(@Valid @RequestBody RegistrationRequestDTO registrationRequest) {
//        try {
//            // Check if email already exists
//            if (userService.findByEmail(registrationRequest.getEmail()).isPresent()) {
//                return ResponseEntity.badRequest()
//                     .body(new APIResponse(false, "Email already registered"));
//            }
//
//            // Create user entity
//            User user = new User(
//                 registrationRequest.getFirstName(),
//                 registrationRequest.getLastName(),
//                 registrationRequest.getEmail(),
//                 registrationRequest.getPhone(),
//                 registrationRequest.getPassword()
//            );
//
//            // Register user
//            User registeredUser = userService.registerUser(user);
//
//            return ResponseEntity.ok()
//                 .body(new AuthenticationResponse(true, "Registration successful. Please check your email to verify your account."));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest()
//                 .body(new AuthenticationResponse(false, "Registration failed: " + e.getMessage()));
//        }
//    }
////
//    @GetMapping("/verify")
//    public ResponseEntity<AuthenticationResponse> verifyUser(@RequestParam String token) {
//        boolean isVerified = userService.verifyUser(token);
//
//        if (isVerified) {
//            return ResponseEntity.ok()
//                 .body(new AuthenticationResponse(true, "Account verified successfully"));
//        } else {
//            return ResponseEntity.badRequest()
//                 .body(new AuthenticationResponse(false, "Invalid verification token"));
//        }
//    }
//
}

