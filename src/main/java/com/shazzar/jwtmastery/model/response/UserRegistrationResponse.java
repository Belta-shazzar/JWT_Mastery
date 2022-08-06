package com.shazzar.jwtmastery.model.response;

import lombok.Data;

@Data
public class UserRegistrationResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
}
