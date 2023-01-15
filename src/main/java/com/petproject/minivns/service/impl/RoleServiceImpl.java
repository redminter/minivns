package com.petproject.minivns.service.impl;

import com.petproject.minivns.entities.Role;
import com.petproject.minivns.repositories.RoleRepository;
import com.petproject.minivns.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role){
            return roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with id " +id+ " is not found")
        );
    }
    @Override
    public Role getByName(String name){
        Role role = roleRepository.findByName(name);
        if(role != null)
        return role;
        else{
            throw  new  ResponseStatusException(HttpStatus.NOT_FOUND, "Role with name " + name + " is not found");
        }
    }

    @Override
    public Role update(Role role) {
        List<Integer> ids = getAll().stream()
                .map(Role::getId).toList();
        if(role == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Some information was typed wrong and role cannot be updated");
        }
        else if (!ids.contains(role.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is  not found and cannot be updated");
        }
        else {
            getById(role.getId()).setName(role.getName());
            return roleRepository.save(role);
        }
    }
    @Override
    public void delete(Integer id) {
        Role role = getById(id);
        if(role != null && getAll().contains(role)) {
            roleRepository.delete(role);
        }
        if(role == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no role to delete");
        }
        else if (!getAll().contains(role)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found and cannot be deleted");
        }
    }

}
