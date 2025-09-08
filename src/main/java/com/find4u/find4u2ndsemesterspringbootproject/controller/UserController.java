package com.find4u.find4u2ndsemesterspringbootproject.controller;

import com.kp.find4uspringbootproject.dto.UserDTO;
import com.kp.find4uspringbootproject.enums.UserStatus;
import com.kp.find4uspringbootproject.service.UserService;
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

    //  Save
    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.ok("✅ User saved successfully!");
    }
    
    //  update
    @PutMapping("update")
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO); // 🔥 Will throw if not found
        return ResponseEntity.ok("🔁 User updated successfully.");
    }
    
    //  Delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("🗑️ User deleted: " + id);
    }
    
    //  Get all
    @GetMapping("getAll")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    // Search by keyword
    @GetMapping("search/{keyword}")
    public ResponseEntity<List<UserDTO>> search(@PathVariable String keyword) {
        return ResponseEntity.ok(userService.getAllByKeyword(keyword));
    }
    
    //  Change status (like ACTIVE → INACTIVE)
    @PatchMapping("{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable String id, @RequestParam UserStatus status) {
        userService.updateUserStatusById(id, status);
        return ResponseEntity.ok("✅ Status updated to: " + status);
    }
    
}

