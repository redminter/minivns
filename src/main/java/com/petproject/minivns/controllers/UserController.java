package com.petproject.minivns.controllers;

import com.petproject.minivns.entities.User;
import com.petproject.minivns.json.UserRequest;
import com.petproject.minivns.json.UserResponse;

import com.petproject.minivns.service.RoleService;
import com.petproject.minivns.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    final UserService userService;
    final RoleService roleService;
    
    final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    //@PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<UserResponse> getAll(){
        return userService.getAll().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Validated @RequestBody UserRequest userRequest, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data is bad entered");
        }
        User newUser = new User();

        newUser.setFirstName(userRequest.getFirst_name());
        newUser.setLastName(userRequest.getLast_name());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        if(!(userRequest.getRole_Id()==null)) {
            newUser.setRole_Id(roleService.getById(userRequest.getRole_Id()));
        }
        else{
            newUser.setRole_Id(roleService.getById(2));
        }
        userService.create(newUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new UserResponse(newUser));
    }

    @GetMapping("/{user_id}")
    public UserResponse getOne(@PathVariable("user_id") Integer userId) {
        try {
            return new UserResponse(userService.getById(userId));
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user with that id");
        }
    }

    @PutMapping({"/{user_id}"})
    @ResponseStatus ( HttpStatus.OK )
    public ResponseEntity<?> update(@PathVariable("user_id") Integer userId, @Valid @RequestBody UserRequest userRequest, BindingResult result) {
        try {
                userService.getById(userId);
            }catch (RuntimeException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user with that id");
        }
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data is bad entered");
        }
        User updatedUser = userService.getById(userId);
        updatedUser.setFirstName(userRequest.getFirst_name());
        updatedUser.setLastName(userRequest.getLast_name());
        updatedUser.setEmail(userRequest.getEmail());
        updatedUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        updatedUser.setRole_Id(roleService.getById(userRequest.getRole_Id()));
        userService.update(updatedUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{user_id}")
                .buildAndExpand(updatedUser.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new UserResponse(updatedUser));
    }

    @DeleteMapping("/{user_id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    void delete (@PathVariable("user_id") Integer user_id) {
       try {
           userService.delete(user_id);
       }catch(RuntimeException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user with that id");
       }
    }
}
