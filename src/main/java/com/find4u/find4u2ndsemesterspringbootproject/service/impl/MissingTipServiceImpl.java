package com.find4u.find4u2ndsemesterspringbootproject.service.impl;

import com.kp.find4uspringbootproject.dto.MissingTipDTO;
import com.kp.find4uspringbootproject.entity.MissingTip;
import com.kp.find4uspringbootproject.repository.MissingTipRepository;
import com.kp.find4uspringbootproject.service.MissingTipService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissingTipServiceImpl implements MissingTipService {
     
     private final MissingTipRepository missingTipRepo;
     private final ModelMapper modelMapper;
     
     @Override
     public void saveMissingTip(MissingTipDTO missingTipDTO) {
          MissingTip tip = modelMapper.map(missingTipDTO, MissingTip.class);
          missingTipRepo.save(tip);
     }
     
}

