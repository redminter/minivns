package com.petproject.minivns.service;

import com.petproject.minivns.entities.User;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> getAll();

    User getById(Integer id);

    User update(User user);

    void delete(Integer id);

    User findByEmail(String email);
}
