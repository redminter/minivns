package com.petproject.minivns.json;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}