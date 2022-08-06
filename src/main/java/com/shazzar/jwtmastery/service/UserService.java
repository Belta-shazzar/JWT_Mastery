package com.shazzar.jwtmastery.service;

import com.shazzar.jwtmastery.model.request.UserRequest;
import com.shazzar.jwtmastery.model.response.NonRegistrationUserResponse;
import com.shazzar.jwtmastery.model.response.UserRegistrationResponse;

import java.util.List;

public interface UserService {
    UserRegistrationResponse officialSignup(UserRequest request);

    UserRegistrationResponse casualSignup(UserRequest request);

    NonRegistrationUserResponse getbyId(Long id);

    List<NonRegistrationUserResponse> getAllUser();

    void deleteById(Long id);
}
