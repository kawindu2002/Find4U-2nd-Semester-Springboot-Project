package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import com.find4u.find4u2ndsemesterspringbootproject.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
     
     private final JavaMailSender mailSender;

//  ==================================================================================================================

     @Override
     public void sendVerificationOtp(String to, String otp) {
          
          SimpleMailMessage message = new SimpleMailMessage();
          message.setTo(to);
          message.setSubject("Verify Your Find4U Account");
          message.setText("Please enter this OTP code to verify your account: " + otp);
 
          mailSender.send(message);
          System.out.println("Email sent successfully to " + to);
     }

//  ==================================================================================================================

//     @Override
//     public void sendVerificationEmail(String to, String token) {
////          String verificationUrl = "http://localhost:8080/api/auth/verify?token=" + token;
//
//          String verificationUrl = "http://localhost:8080/api/auth/verify?token=" + token;
//
//          SimpleMailMessage message = new SimpleMailMessage();
//          message.setTo(to);
//          message.setSubject("Verify Your Find4U Account");
//          message.setText("Please click the following link to verify your account: " + verificationUrl);
//
//          mailSender.send(message);
//     }

//  ==================================================================================================================

}

