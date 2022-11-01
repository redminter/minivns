package com.petproject.minivns.repositories;


import com.petproject.minivns.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
