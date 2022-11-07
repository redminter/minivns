package com.petproject.minivns.service;

import com.petproject.minivns.entities.Role;

import java.util.List;

public interface RoleService {

    Role addRole(Role role);
    public List<Role> getAll();
    public Role getById(Integer id);

    Role getByName(String name);

    //
    Role update(Role role);

    void delete(Integer id);
}
