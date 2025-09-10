
package com.find4u.find4u2ndsemesterspringbootproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MissingPersonDTO {
     
     private Long id;
     private String fullName;
     private Integer age;
     private String gender;
     private String height;
     private String weight;
     private String hairColor;
     private String eyeColor;
     private String distinguishingFeatures;
     private String photoUrl;
     private String status;
     
     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate missingDate;
     @JsonFormat(pattern = "hh:mm a")  // 'hh' = 12-hour format | 'a' = AM/PM
     private LocalTime missingTime;
     
     private String location;
     private String locationLatitude;
     private String locationLongitude;
     
     private String nearByLandmarks;
     private String clothing;
     private String circumstances;
     
     private Long reporterId;    // FK only reporter's ID = User id --> username, email and contact number
     private String relationToMissingPerson; // Ex: Brother, Sister
     private String reward;
     
     private Boolean isAcceptedTermPolicy;
     private Boolean isConfirmedDetails;
     
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;
     
}

// In map, We send the location and gets its coordinates for the map using Ajax GET.

