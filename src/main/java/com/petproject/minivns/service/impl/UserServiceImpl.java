package com.petproject.minivns.service.impl;

import com.petproject.minivns.entities.User;
import com.petproject.minivns.repositories.UserRepository;
import com.petproject.minivns.service.RoleService;
import com.petproject.minivns.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService)
    {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User addUser(User user) {
        if(user.getRole_Id()==null){
            user.setRole_Id(roleService.getById(2));
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User id not found")
        );
    }

    @Override
    public User update (User user) {
        List<Integer> ids = getAll().stream()
                .map(User::getId).collect(Collectors.toList());
        if (user != null && ids.contains(user.getId())) {
            getById(user.getId()).setFirstName(user.getFirstName());
            getById(user.getId()).setLastName(user.getLastName());
            getById(user.getId()).setPassword(user.getPassword());
            getById(user.getId()).setEmail(user.getEmail());
            if(user.getRole_Id()==null){
                getById(user.getId()).setRole_Id(roleService.getById(2));
                user.setRole_Id(roleService.getById(2));
            }
            return userRepository.save(user);
        }
        throw new RuntimeException("User is null or not found and cannot be updated");
    }
    @Override
    public void delete(Integer id) {
        User user = getById(id);
        if(user != null && getAll().contains(user)) {
            userRepository.delete(user);
        }
        else{
            throw new RuntimeException("User is null or not found and cannot be deleted");
        }
    }
}
