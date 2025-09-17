package com.find4u.find4u2ndsemesterspringbootproject.controller;

import com.find4u.find4u2ndsemesterspringbootproject.dto.APIResponse;
import com.find4u.find4u2ndsemesterspringbootproject.dto.AuthRequestDTO;
import com.find4u.find4u2ndsemesterspringbootproject.dto.RegistrationRequestDTO;
import com.find4u.find4u2ndsemesterspringbootproject.dto.UserDTO;
import com.find4u.find4u2ndsemesterspringbootproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<APIResponse> registerUser(@Valid @RequestBody RegistrationRequestDTO registrationRequestDto) {
        // Check if email already exists
        if (userService.findByEmail(registrationRequestDto.getEmail()).isPresent()) {
            return ResponseEntity
                 .badRequest()
                 .body(new APIResponse(400, "Email already registered", null));
            
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
        return ResponseEntity.ok(new APIResponse(200, userService.registerUser(userDto), null));
        
    }

//  ==================================================================================================================
    
    @PostMapping("/verify")
    public ResponseEntity<APIResponse> verifyUser(@RequestParam String email, @RequestParam String otp) {
        boolean isVerified = userService.verifyUser(email, otp);
        
        if (isVerified) {
            return ResponseEntity.ok(new APIResponse(200, "Account verified successfully!", null));
        } else {
            return ResponseEntity.badRequest()
                 .body(new APIResponse(400, "Invalid verification OTP or email", null));
        }
    }

//  ==================================================================================================================
    
    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(new APIResponse(200, "Account verified successfully!", userService.loginUser(authRequestDTO)));
   
    }

//  ==================================================================================================================

}

