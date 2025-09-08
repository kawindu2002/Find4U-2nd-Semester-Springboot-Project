package com.find4u.find4u2ndsemesterspringbootproject.controller;

import com.find4u.find4u2ndsemesterspringbootproject.dto.APIResponse;
import com.find4u.find4u2ndsemesterspringbootproject.dto.MissingTipDTO;
import com.find4u.find4u2ndsemesterspringbootproject.service.MissingTipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/find4u/tip")
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor

public class MissingTipController {
     
     private final MissingTipService missingTipService;
     
     @PostMapping("save")
     public ResponseEntity<APIResponse> save(@RequestBody MissingTipDTO missingTipDTO) {
          missingTipService.saveMissingTip(missingTipDTO);
          return new ResponseEntity(new APIResponse(200, "Tip saved successfully!", null), HttpStatus.OK);
          
     }
     
//     There are more (save by id, delete by id,update by id,search by id)

}

