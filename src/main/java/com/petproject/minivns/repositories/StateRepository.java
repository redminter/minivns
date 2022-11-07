package com.petproject.minivns.repositories;


import com.petproject.minivns.entities.Role;
import com.petproject.minivns.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
    State findByName(String name);
}
