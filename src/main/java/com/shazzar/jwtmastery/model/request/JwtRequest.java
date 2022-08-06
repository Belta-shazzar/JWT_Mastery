package com.shazzar.jwtmastery.model.request;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;
}
