package com.find4u.find4u2ndsemesterspringbootproject.service;

import com.find4u.find4u2ndsemesterspringbootproject.dto.MissingPersonDTO;
import com.find4u.find4u2ndsemesterspringbootproject.enums.PersonStatus;

import java.util.List;

public interface MissingPersonService {
     
     void saveMissingPerson(MissingPersonDTO missingPersonDTO);
     
     void updateMissingPerson(MissingPersonDTO missingPersonDTO);
     
     boolean isExistMissingPerson(String missingPersonId);
     
     void deleteMissingPerson(String missingPersonId);
     
     List<MissingPersonDTO> getAllMissingPeople();
     
     List<MissingPersonDTO> getAllByKeyword(String keyword);
     
     void updateMissingPersonStatusById(String id, PersonStatus newStatus);
     
}

