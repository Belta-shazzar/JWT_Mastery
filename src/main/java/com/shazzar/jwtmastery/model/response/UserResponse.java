package com.shazzar.jwtmastery.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
}
