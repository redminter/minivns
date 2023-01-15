package com.petproject.minivns.service.impl;

import com.petproject.minivns.entities.User;
import com.petproject.minivns.repositories.UserRepository;
import com.petproject.minivns.service.RoleService;
import com.petproject.minivns.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User create(User user) {
        List<String> emails = getAll().stream()
                .map(User::getEmail).toList();
        if(emails.contains(user.getEmail())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "There is such email registered already");
        }
        else{
            return userRepository.save(user);
        }

    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " not found")
        );
    }

    @Override
    public User update(User user) {
        List<User> userList = getAll();
        userList.remove(user);
        List<String> emails = userList.stream()
                .map(User::getEmail).toList();

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Some information was typed wrong and user cannot be updated");
        } else if (!getAll().stream().map(User::getId).toList().contains(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id:" + user.getId() + " is not found and cannot be updated");
        } else {
            getById(user.getId()).setFirstName(user.getFirstName());
            getById(user.getId()).setLastName(user.getLastName());
            getById(user.getId()).setPassword(user.getPassword());
            if(emails.contains(user.getEmail()))
            {
                throw  new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User with such email address already exists");
            }
            else {
                getById(user.getId()).setEmail(user.getEmail());
            }
            if (user.getRole_Id() == null) {
                getById(user.getId()).setRole_Id(roleService.getById(2));
                user.setRole_Id(roleService.getById(2));
            }
            return userRepository.save(user);
        }
    }

    @Override
    public void delete(Integer id) {
        User user = getById(id);
        if (!getAll().contains(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found and cannot be deleted");
        } else {
            userRepository.delete(user);
        }
    }

    //    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//        User user = userRepository.findByEmail(username);
//        if(user==null){
//          throw new ResponseStatusException(HttpStatus.NO_CONTENT, "User with username "+username+" not found");
//        }
//        return user;
//    }
    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email:" + email + " is not found");
        } else {
            return user;
        }
    }
}
