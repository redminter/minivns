package com.petproject.minivns.controllers;

import com.petproject.minivns.entities.User;
import com.petproject.minivns.json.UserRequest;
import com.petproject.minivns.json.UserResponse;

import com.petproject.minivns.service.RoleService;
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
@RequestMapping("/users")
public class UserController {
    final UserService userService;
    final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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

        newUser.setFirstName(userRequest.getFirst_name());
        newUser.setLastName(userRequest.getLast_name());
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(userRequest.getPassword());
        newUser.setRole_Id(roleService.getById(userRequest.getRole_Id()));
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
        user.setFirstName(userRequest.getFirst_name());
        user.setLastName(userRequest.getLast_name());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole_Id(roleService.getById(userRequest.getRole_Id()));
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
