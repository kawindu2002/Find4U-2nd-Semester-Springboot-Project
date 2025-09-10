package com.find4u.find4u2ndsemesterspringbootproject.service;

import com.find4u.find4u2ndsemesterspringbootproject.dto.MissingPersonDTO;

import java.util.List;

public interface MissingPersonService {
     
     void saveMissingPerson(MissingPersonDTO missingPersonDTO);
     
     void updateMissingPerson(MissingPersonDTO missingPersonDTO);
     
     boolean isExistMissingPerson(Long missingPersonId);
     
     void deleteMissingPerson(Long missingPersonId);
     
     List<MissingPersonDTO> getAllMissingPeople();
     
     void updateMissingPersonStatusById(Long id, String newStatus);
     
}

