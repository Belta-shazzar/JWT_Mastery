package com.shazzar.jwtmastery.model;


import com.shazzar.jwtmastery.entity.User;
import com.shazzar.jwtmastery.model.request.UserRequest;
import com.shazzar.jwtmastery.model.response.UserResponse;

public class Mapper {

    public static User userRequestToUser(UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        return user;
    }

    public static UserResponse userResponseWithJwt(User user, String generatedToken) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setToken(generatedToken);

        return response;
    }
}
