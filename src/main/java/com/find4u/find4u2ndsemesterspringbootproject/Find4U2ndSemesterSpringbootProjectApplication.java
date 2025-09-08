package com.find4u.find4u2ndsemesterspringbootproject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Find4U2ndSemesterSpringbootProjectApplication {
     
     public static void main(String[] args) {
          SpringApplication.run(Find4U2ndSemesterSpringbootProjectApplication.class, args);
     }
     
     @Bean
     public ModelMapper modelMapper() {
          return new ModelMapper();
     }
}

