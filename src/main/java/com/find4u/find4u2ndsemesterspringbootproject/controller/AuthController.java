package com.find4u.find4u2ndsemesterspringbootproject.controller;

import com.find4u.find4u2ndsemesterspringbootproject.dto.APIResponse;
import com.find4u.find4u2ndsemesterspringbootproject.dto.RegistrationRequestDTO;
import com.find4u.find4u2ndsemesterspringbootproject.dto.UserDTO;
import com.find4u.find4u2ndsemesterspringbootproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/find4u/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final UserService userService;
    
//  ==================================================================================================================
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationRequestDTO registrationRequestDto) {
        // Check if email already exists
        if (userService.findByEmail(registrationRequestDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        
        // Create user Dto
        UserDTO userDto = new UserDTO(
             registrationRequestDto.getFirstName(),
             registrationRequestDto.getLastName(),
             registrationRequestDto.getEmail(),
             registrationRequestDto.getPhone(),
             registrationRequestDto.getPassword(),
             registrationRequestDto.isAgreedTermsPolicy()
        );
        
        // Add verification otp to user
        userDto.setVerificationOTP(userService.generateVerificationOtp());
        
        // Register user
        userService.registerUser(userDto);
        
        return new ResponseEntity(new APIResponse(200, "Registration successful. Please check your email to verify your account. ", null), HttpStatus.OK);
        
        
    }
    
//  ==================================================================================================================

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

//  ==================================================================================================================


}

