package com.shazzar.jwtmastery.model.response;

import lombok.Data;

@Data
public class NonRegistrationUserResponse {
    private String firstName;
    private String lastName;
    private String email;
}
