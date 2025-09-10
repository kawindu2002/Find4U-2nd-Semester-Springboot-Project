package com.find4u.find4u2ndsemesterspringbootproject.service;

public interface EmailService {
     void sendVerificationEmail(String to, String token);
     void sendVerificationToken(String to, String token);

}

