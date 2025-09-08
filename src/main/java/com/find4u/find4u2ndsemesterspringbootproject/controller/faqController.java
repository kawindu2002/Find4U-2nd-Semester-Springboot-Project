package com.find4u.find4u2ndsemesterspringbootproject.controller;

import com.find4u.find4u2ndsemesterspringbootproject.dto.APIResponse;
import com.find4u.find4u2ndsemesterspringbootproject.dto.FaqDTO;
import com.find4u.find4u2ndsemesterspringbootproject.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/find4u/faq")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class faqController {
     
     private final FaqService faqService;
     
     // Get all
     @GetMapping("getAll")
     public ResponseEntity<APIResponse> getAll() {
          List<FaqDTO> faqList = faqService.getAllFaqs();
          return new ResponseEntity(new APIResponse(200, "Success", faqList), HttpStatus.OK);
     }
}

