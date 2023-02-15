package com.petproject.minivns.json;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateRequest {

    private String first_name;

    private String last_name;

    private String email;

    private String password;

}
