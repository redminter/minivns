package com.petproject.minivns.json;


import com.petproject.minivns.entities.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
    @NotBlank(message = "The first name cannot be empty")
    private String first_name;
    @NotBlank(message = "The last name cannot be empty")
    private String last_name;
    @NotBlank(message = "The email cannot be empty")
    private String email;
    @NotBlank(message = "The password cannot be empty")
    private String password;
    private Integer role_Id;
}
