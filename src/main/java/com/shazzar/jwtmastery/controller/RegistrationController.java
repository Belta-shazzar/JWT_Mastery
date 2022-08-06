package com.shazzar.jwtmastery.controller;

import com.shazzar.jwtmastery.config.userdetailconfig.AppUserService;
import com.shazzar.jwtmastery.model.request.JwtRequest;
import com.shazzar.jwtmastery.model.request.UserRequest;
import com.shazzar.jwtmastery.model.response.UserRegistrationResponse;
import com.shazzar.jwtmastery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;
    private final AppUserService appUserService;


    @PostMapping("/officialSignup")
    public ResponseEntity<UserRegistrationResponse> officialSignup(@RequestBody UserRequest request) {
        UserRegistrationResponse response = userService.officialSignup(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/casualSignup")
    public ResponseEntity<UserRegistrationResponse> casualSignup(@RequestBody UserRequest request) {
        UserRegistrationResponse response = userService.casualSignup(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserRegistrationResponse> signInAuthentication(@RequestBody JwtRequest request) throws Exception {
        UserRegistrationResponse response = appUserService.signInAuthentication(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
