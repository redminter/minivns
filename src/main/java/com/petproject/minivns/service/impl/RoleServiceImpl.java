package com.petproject.minivns.service.impl;

import com.petproject.minivns.entities.Role;
import com.petproject.minivns.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements com.petproject.minivns.service.RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role addRole(Role role){
            return roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Role id not found")
        );
    }
    @Override
    public Role getByName(String name){
        Role role = roleRepository.findByName(name);
        if(role != null)
        return role;
        else{
            throw new RuntimeException("Role name not found");
        }
    }

    @Override
    public Role update(Role role) {
        List<Integer> ids = getAll().stream()
                .map(Role::getId).collect(Collectors.toList());
        if (role != null && ids.contains(role.getId())) {
            getById(role.getId()).setName(role.getName());
            return roleRepository.save(role);
        }
        throw new RuntimeException("Role is null of not found and cannot be updated");
    }
    @Override
    public void delete(Integer id) {
        Role role = getById(id);
        if(role != null && getAll().contains(role)) {
            roleRepository.delete(role);
        }
        else{
            throw new RuntimeException("Role is null of not found and cannot be deleted");
        }
    }

}
