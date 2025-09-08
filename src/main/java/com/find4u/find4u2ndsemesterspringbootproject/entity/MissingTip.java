
package com.find4u.find4u2ndsemesterspringbootproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "missing_tip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MissingTip {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate date;
     
     private String tip;
     private String source;
     
     @ManyToOne
     @JoinColumn(name = "missing_person_id")
     private MissingPerson missingPerson;
     
}

