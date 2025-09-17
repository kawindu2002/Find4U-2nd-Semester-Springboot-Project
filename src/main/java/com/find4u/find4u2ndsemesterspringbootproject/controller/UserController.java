package com.find4u.find4u2ndsemesterspringbootproject.controller;

import com.find4u.find4u2ndsemesterspringbootproject.dto.UserDTO;
import com.find4u.find4u2ndsemesterspringbootproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/find4u/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

//  ==================================================================================================================
    
    //  Save
    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.ok("User saved successfully!");
    }
    
    //  update
    @PutMapping("update")
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return ResponseEntity.ok("User updated successfully.");
    }
    
    //  Delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted: " + id);
    }
    
    //  Get all
    @GetMapping("getAll")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

//  ==================================================================================================================
    
    //  Change status (like ACTIVE → INACTIVE)
    @PatchMapping("{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam String status) {
        userService.updateUserStatusById(id, status);
        return ResponseEntity.ok("Status updated to: " + status);
    }

//  ==================================================================================================================

}


