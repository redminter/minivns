package com.petproject.minivns.controllers;

import com.petproject.minivns.entities.User;
import com.petproject.minivns.json.UserRequest;
import com.petproject.minivns.json.UserResponse;

import com.petproject.minivns.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/user")
public class UserController {
    final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAll(){
        return userService.getAll().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Validated @RequestBody UserRequest userRequest, BindingResult result){
        if(result.hasErrors()){ throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data is bad entered");}
        User newUser = new User();
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(userRequest.getPassword());
        newUser.setRole_Id(userRequest.getRole_Id());
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
        return new UserResponse(userService.getById(userId));
    }

    @PutMapping({"/{user_id}"})
    @ResponseStatus ( HttpStatus.OK )
    public ResponseEntity<?> update(@PathVariable("user_id") Integer user_id, @Valid @RequestBody UserRequest userRequest) {
        User user = userService.getById(user_id);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole_Id(userRequest.getRole_Id());
        userService.update(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{user_id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new UserResponse(user));
    }

    @DeleteMapping("/{user_id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    void delete (@PathVariable("user_id") Integer user_id) {
        userService.delete(user_id);
    }
}
