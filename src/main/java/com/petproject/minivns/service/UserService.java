package com.petproject.minivns.service;

import com.petproject.minivns.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> getAll();

    User getById(Integer id);

    User update(User user);

    void delete(Integer id);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
