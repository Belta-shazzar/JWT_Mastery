package com.shazzar.jwtmastery.service;

import com.shazzar.jwtmastery.model.request.UserRequest;
import com.shazzar.jwtmastery.model.response.UserResponse;

public interface UserService {
    UserResponse officialSignup(UserRequest request);

    UserResponse casualSignup(UserRequest request);

}
