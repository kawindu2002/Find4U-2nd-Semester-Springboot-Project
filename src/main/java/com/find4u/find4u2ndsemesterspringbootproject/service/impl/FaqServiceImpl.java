package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import com.kp.find4uspringbootproject.dto.FaqDTO;
import com.kp.find4uspringbootproject.entity.Faq;
import com.kp.find4uspringbootproject.repository.FaqRepository;
import com.kp.find4uspringbootproject.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {
     
     private final FaqRepository faqRepo;
     private final ModelMapper modelMapper;
     
     @Override
     public List<FaqDTO> getAllFaqs() {
          List<Faq> allfaqs = faqRepo.findAll();
          return modelMapper.map(allfaqs, new TypeToken<List<FaqDTO>>() {}.getType());
     }

}

