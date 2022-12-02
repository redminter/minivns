package com.petproject.minivns.repositories;


import com.petproject.minivns.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
     //@Query(value = "select id, title, link, deadline, state_id, user_id, subject_id  from tasks where user_id = ?1;",nativeQuery = true)
     List<Task> getTasksByUser_id(Integer userId);
     List<Task> getTasksBySubjectBySubjectId(Integer userId);
}
