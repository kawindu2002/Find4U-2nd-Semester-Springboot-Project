package com.find4u.find4u2ndsemesterspringbootproject.controller;

import com.find4u.find4u2ndsemesterspringbootproject.dto.APIResponse;
import com.find4u.find4u2ndsemesterspringbootproject.dto.MissingPersonDTO;
import com.find4u.find4u2ndsemesterspringbootproject.enums.PersonStatus;
import com.find4u.find4u2ndsemesterspringbootproject.service.MissingPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/find4u/missing")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MissingPersonController {

    private final MissingPersonService missingPersonService;
    
    // Save
    @PostMapping("save")
    public ResponseEntity<APIResponse> save(@RequestBody MissingPersonDTO missingPersonDTO) {
        missingPersonService.saveMissingPerson(missingPersonDTO);
        return new ResponseEntity(new APIResponse(200, "Missing person saved successfully!", null), HttpStatus.OK);
    }
    
    // Update
    @PutMapping("update")
    public ResponseEntity<APIResponse> update(@RequestBody MissingPersonDTO missingPersonDTO) {
        missingPersonService.updateMissingPerson(missingPersonDTO); // Will throw if not found
        return new ResponseEntity(new APIResponse(200, "Missing person updated successfully.", null), HttpStatus.OK);
    }
    
    // Delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable String id) {
        missingPersonService.deleteMissingPerson(id);
        return new ResponseEntity(new APIResponse(200, "Missing person deleted: " + id, null), HttpStatus.OK);
    }

    // Get all
    @GetMapping("getAll")
    public ResponseEntity<APIResponse> getAll() {
        List<MissingPersonDTO> missingList = missingPersonService.getAllMissingPeople();
        return new ResponseEntity(new APIResponse(200, "Success", missingList), HttpStatus.OK);
    }

    // Search by keyword
    @GetMapping("search/{keyword}")
    public ResponseEntity<APIResponse> search(@PathVariable String keyword) {
        missingPersonService.getAllByKeyword(keyword);
        return new ResponseEntity(new APIResponse(200, "Missing Person Found Successfully", null), HttpStatus.OK);
    }

    // Change status (like MISSING → FOUND)
    @PatchMapping("{id}/status")
    public ResponseEntity<APIResponse> updateStatus(@PathVariable String id, @RequestParam PersonStatus status) {
        missingPersonService.updateMissingPersonStatusById(id, status);
        return new ResponseEntity(new APIResponse(200, "Status updated to: " + status, null), HttpStatus.OK);
    
    }
}

