
package com.find4u.find4u2ndsemesterspringbootproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missing_person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MissingPerson {
     
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
     private String fullName;
     private Integer age;
     
     private String gender;
     
     private String height;
     private String weight;
     
     @Column(name = "hair_color")
     private String hairColor;
     
     @Column(name = "eye_color")
     private String eyeColor;
     
     @Column(name = "distinguishing_features")
     private String distinguishingFeatures;
     
     @Column(name = "photo_url")
     private String photoUrl;
     
     private String status = "missing";
     
     @Column(name = "missing_date")
     private LocalDate missingDate;
     
     @Column(name = "missing_time")
     private LocalTime missingTime;
     
     private String location;
     private String nearByLandmarks;
     private String clothing;
     private String circumstances;
     
     // FK only reporter's ID = User id --> username, email and contact number
     @ManyToOne
     @JoinColumn(name = "reporter_id")
     private User user;
     
     @Column(name = "relation_to_missing_person")
     private String relationToMissingPerson; // Ex: Brother, Sister
     private String reward;
     
     @Column(name = "accepted_term_policy")
     private Boolean isAcceptedTermPolicy = false;
     
     @Column(name = "confirmed_details")
     private Boolean isConfirmedDetails = false;
     
     @Column(name = "created_at",updatable = false)
     private LocalDateTime createdAt;
     
     @Column(name = "updated_at")
     private LocalDateTime updatedAt;
     
     @OneToMany(mappedBy = "missingPerson", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<MissingTip> tips = new ArrayList<>();

}

