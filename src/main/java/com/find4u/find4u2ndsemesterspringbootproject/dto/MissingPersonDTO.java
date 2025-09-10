
package com.find4u.find4u2ndsemesterspringbootproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.find4u.find4u2ndsemesterspringbootproject.enums.Gender;
import com.find4u.find4u2ndsemesterspringbootproject.enums.PersonStatus;
import com.find4u.find4u2ndsemesterspringbootproject.enums.YesNo;
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
     private Gender gender;
     private String height;
     private String weight;
     private String hairColor;
     private String eyeColor;
     private String distinguishingFeatures;
     private String photoUrl;
     private PersonStatus status;
     
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
     
     private YesNo acceptedTermPolicy;
     private YesNo confirmedDetails;
     
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;
     
}

// In map, We send the location and gets its coordinates for the map using Ajax GET.

