package com.petproject.minivns.json;


import com.petproject.minivns.entities.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
    @NotBlank(message = "The first name cannot be empty")
    private String firstName;
    @NotBlank(message = "The last name cannot be empty")
    private String lastName;
    @NotBlank(message = "The email cannot be empty")
    private String email;
    @NotBlank(message = "The password cannot be empty")
    private String password;
    @NotBlank(message = "The role_id cannot be empty")
    private Role role_Id;
}
