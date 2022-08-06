package com.shazzar.jwtmastery.controller;

import com.shazzar.jwtmastery.model.response.NonRegistrationUserResponse;
import com.shazzar.jwtmastery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("/{id}")
    public ResponseEntity<NonRegistrationUserResponse> getById(@PathVariable("id") Long id) {
        NonRegistrationUserResponse response = userService.getbyId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping
    public ResponseEntity<List<NonRegistrationUserResponse>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Role_OFFICIAL')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        String deleteResponse = "User with the id " + id + " have been deleted successfully";
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }
}
