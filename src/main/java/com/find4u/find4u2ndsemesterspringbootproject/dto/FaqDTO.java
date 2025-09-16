package com.find4u.find4u2ndsemesterspringbootproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class FaqDTO {
     private Long id;
     private String question;
     private String answer;
     
}

