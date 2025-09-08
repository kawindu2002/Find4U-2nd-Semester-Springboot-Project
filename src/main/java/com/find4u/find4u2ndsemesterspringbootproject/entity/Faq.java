package com.find4u.find4u2ndsemesterspringbootproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "faq")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Faq {
     
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
     @Column(nullable = false)
     private String question;
     
     @Column(nullable = false)
     private String answer;
     
}

