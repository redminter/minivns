package com.petproject.minivns.controllers;

import com.petproject.minivns.entities.User;
import com.petproject.minivns.json.AuthenticationRequest;
import com.petproject.minivns.security.jwt.JwtTokenProvider;
import com.petproject.minivns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest requestDto) throws ResponseStatusException {
            String email = requestDto.getUsername();

            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + email + " not found");
            }
           try{
               authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
           }catch (AuthenticationException e){
               throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid username or password");
           }

            String token = jwtTokenProvider.createToken(email, user.getRole_Id());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", email);
            response.put("token", token);

            return ResponseEntity.ok(response);

    }
}
