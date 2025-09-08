package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import com.kp.find4uspringbootproject.dto.MissingPersonDTO;
import com.kp.find4uspringbootproject.entity.MissingPerson;
import com.kp.find4uspringbootproject.enums.PersonStatus;
import com.kp.find4uspringbootproject.exception.NotFoundException;
import com.kp.find4uspringbootproject.repository.MissingPersonRepository;
import com.kp.find4uspringbootproject.service.MissingPersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissingPersonServiceImpl implements MissingPersonService {
     
     private final MissingPersonRepository missingPersonRepo;
     private final ModelMapper modelMapper;
     
     @Override
     public String generateCustomId() {
          String prefix = "MIS";
          String year = String.valueOf(LocalDate.now().getYear()); // MIS-2025-%
          String likePattern = prefix + "-" + year + "-";
          // Count how many already exist with this year
          long count = missingPersonRepo.countByIdStartingWith(likePattern);
          long next = count + 1; // Get the next number in line
          String sequence = String.format("%05d", next); // Pad the number to 5 digits: 00001, 00002, etc.
          return String.format("%s-%s-%s", prefix, year, sequence); // Final ID: MIS-2025-00001
     }
     
     @Override
     public void saveMissingPerson(MissingPersonDTO missingPersonDTO) {
        String customId = generateCustomId();
        missingPersonDTO.setId(customId);
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
     public boolean isExistMissingPerson(String missingPersonId) {
          return missingPersonRepo.existsById(missingPersonId);
     }
     
     @Override
     public void deleteMissingPerson(String missingPersonId) {
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
     public List<MissingPersonDTO> getAllByKeyword(String keyword) {
          List<MissingPerson> list = missingPersonRepo.findMissingPersonByIdContainingIgnoreCase(keyword);
          return modelMapper.map(list, new TypeToken<List<MissingPersonDTO>>(){}.getType());
     }
     
     @Override
     public void updateMissingPersonStatusById(String missingPersonId, PersonStatus newStatus) {
          missingPersonRepo.findById(missingPersonId)
               .orElseThrow(() -> new NotFoundException("Missing person not found with ID: " + missingPersonId));
          missingPersonRepo.updateMissingPersonStatusById(missingPersonId, newStatus);
     }
     

}

