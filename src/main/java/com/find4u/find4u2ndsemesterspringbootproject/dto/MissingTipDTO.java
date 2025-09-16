package com.find4u.find4u2ndsemesterspringbootproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data

public class MissingTipDTO {
     private Long id;
     
     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate date;
     private String tip;
     private String source;
     
     private Long missingPersonId;
     
}

