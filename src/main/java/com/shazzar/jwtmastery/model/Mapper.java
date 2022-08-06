package com.shazzar.jwtmastery.model;


import com.shazzar.jwtmastery.entity.User;
import com.shazzar.jwtmastery.model.request.UserRequest;
import com.shazzar.jwtmastery.model.response.NonRegistrationUserResponse;
import com.shazzar.jwtmastery.model.response.UserRegistrationResponse;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static User userRequestToUser(UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        return user;
    }

    public static UserRegistrationResponse userResponseWithJwt(User user, String generatedToken) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setToken(generatedToken);

        return response;
    }

    public static NonRegistrationUserResponse userTouserResponse(User user) {
        NonRegistrationUserResponse response = new NonRegistrationUserResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());

        return response;
    }


    public static List<NonRegistrationUserResponse> UsersToUserLIstResponse(List<User> users) {
        List<NonRegistrationUserResponse> responses = new ArrayList<>();

        for (User user : users) {
            responses.add(userTouserResponse(user));
        }

        return responses;
    }
}
