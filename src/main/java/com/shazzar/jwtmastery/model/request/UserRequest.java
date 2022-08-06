package com.shazzar.jwtmastery.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
