package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import com.find4u.find4u2ndsemesterspringbootproject.service.EmailService;
import com.find4u.find4u2ndsemesterspringbootproject.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
     
     
     @Override
     public void sendVerificationEmail(String to, String token) {
     
     }
     
     @Override
     public void sendVerificationToken(String to, String token) {
     
     }
}


package com.find4u.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
     
     @Autowired
     private JavaMailSender mailSender;
     
     public void sendVerificationEmail(String to, String token) {
          String verificationUrl = "http://localhost:8080/api/auth/verify?token=" + token;
          
          SimpleMailMessage message = new SimpleMailMessage();
          message.setTo(to);
          message.setSubject("Verify Your Find4U Account");
          message.setText("Please click the following link to verify your account: " + verificationUrl);
          
          mailSender.send(message);
     }
}