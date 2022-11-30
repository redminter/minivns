package com.petproject.minivns.json;

import com.petproject.minivns.entities.Role;
import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.entities.User;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role_Id;


    public UserResponse(User user) {
        this.firstName= user.getFirstName();
        this.lastName= user.getLastName();
        this.email= user.getEmail();
        this.role_Id=user.getRole_Id();
        this.password= user.getPassword();
    }
}
