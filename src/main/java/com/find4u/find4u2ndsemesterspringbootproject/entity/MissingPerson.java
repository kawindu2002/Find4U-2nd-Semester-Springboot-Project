
package com.find4u.find4u2ndsemesterspringbootproject.entity;

import com.find4u.find4u2ndsemesterspringbootproject.enums.Gender;
import com.find4u.find4u2ndsemesterspringbootproject.enums.PersonStatus;
import com.find4u.find4u2ndsemesterspringbootproject.enums.YesNo;
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
     private String id; // Ex: MIS-2025-00001
     private String fullName;
     private Integer age;
     
     @Enumerated(EnumType.STRING)
     private Gender gender;
     
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
     
     @Enumerated(EnumType.STRING)
     private PersonStatus status = PersonStatus.MISSING;
     
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
     
     
     @Enumerated(EnumType.STRING)
     @Column(name = "accepted_term_policy")
     private YesNo acceptedTermPolicy = YesNo.NO;
     
     @Enumerated(EnumType.STRING)
     @Column(name = "confirmed_details")
     private YesNo confirmedDetails = YesNo.NO;
     
     @Column(name = "created_at",updatable = false)
     private LocalDateTime createdAt;
     
     @Column(name = "updated_at")
     private LocalDateTime updatedAt;
     
     @OneToMany(mappedBy = "missingPerson", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<MissingTip> tips = new ArrayList<>();

}

