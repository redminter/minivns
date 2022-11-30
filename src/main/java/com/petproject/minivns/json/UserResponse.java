package com.petproject.minivns.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.petproject.minivns.entities.Role;
import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.entities.User;
import lombok.NonNull;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Value
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserResponse {

     String first_name;
     String last_name;
     String email;
     String password;
     Role role_Id;


    public UserResponse(User user) {
        this.first_name= user.getFirstName();
        this.last_name= user.getLastName();
        this.email= user.getEmail();
        this.role_Id=user.getRole_Id();
        this.password= user.getPassword();
    }
}
