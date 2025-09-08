package com.find4u.find4u2ndsemesterspringbootproject.service;

import com.kp.find4uspringbootproject.dto.MissingPersonDTO;
import com.kp.find4uspringbootproject.enums.PersonStatus;

import java.util.List;

public interface MissingPersonService {
     
     String generateCustomId();
     
     void saveMissingPerson(MissingPersonDTO missingPersonDTO);
     
     void updateMissingPerson(MissingPersonDTO missingPersonDTO);
     
     boolean isExistMissingPerson(String missingPersonId);
     
     void deleteMissingPerson(String missingPersonId);
     
     List<MissingPersonDTO> getAllMissingPeople();
     
     List<MissingPersonDTO> getAllByKeyword(String keyword);
     
     void updateMissingPersonStatusById(String id, PersonStatus newStatus);
     
}

