package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import com.find4u.find4u2ndsemesterspringbootproject.dto.MissingPersonDTO;
import com.find4u.find4u2ndsemesterspringbootproject.entity.MissingPerson;
import com.find4u.find4u2ndsemesterspringbootproject.enums.PersonStatus;
import com.find4u.find4u2ndsemesterspringbootproject.exception.NotFoundException;
import com.find4u.find4u2ndsemesterspringbootproject.repository.MissingPersonRepository;
import com.find4u.find4u2ndsemesterspringbootproject.service.MissingPersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissingPersonServiceImpl implements MissingPersonService {
     
     private final MissingPersonRepository missingPersonRepo;
     private final ModelMapper modelMapper;
     
     @Override
     public void saveMissingPerson(MissingPersonDTO missingPersonDTO) {
        MissingPerson person = modelMapper.map(missingPersonDTO, MissingPerson.class);
        missingPersonRepo.save(person);
     }
     
     @Override
     public void updateMissingPerson(MissingPersonDTO missingPersonDTO) {
          missingPersonRepo.findById(missingPersonDTO.getId())
               .orElseThrow(() -> new NotFoundException("Missing person not found with ID: " + missingPersonDTO.getId()));
          
          MissingPerson person =  modelMapper.map(missingPersonDTO, MissingPerson.class);
          missingPersonRepo.save(person);
     }
     
     @Override
     public boolean isExistMissingPerson(Long missingPersonId) {
          return missingPersonRepo.existsById(missingPersonId);
     }
     
     @Override
     public void deleteMissingPerson(Long missingPersonId) {
          missingPersonRepo.findById(missingPersonId)
               .orElseThrow(() -> new NotFoundException("Missing person not found with ID: " + missingPersonId));

          missingPersonRepo.deleteById(missingPersonId);
     }
     
     @Override
     public List<MissingPersonDTO> getAllMissingPeople() {
          List<MissingPerson> allMissingPeople = missingPersonRepo.findAll();
          return modelMapper.map(allMissingPeople, new TypeToken<List<MissingPersonDTO>>() {}.getType());
     }
     
     @Override
     public void updateMissingPersonStatusById(Long missingPersonId, PersonStatus newStatus) {
          missingPersonRepo.findById(missingPersonId)
               .orElseThrow(() -> new NotFoundException("Missing person not found with ID: " + missingPersonId));
          missingPersonRepo.updateMissingPersonStatusById(missingPersonId, newStatus);
     }
     

}

