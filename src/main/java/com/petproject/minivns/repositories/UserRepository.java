package com.petproject.minivns.repositories;


import com.petproject.minivns.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String username);
}
