package com.petproject.minivns.service;

import com.petproject.minivns.entities.Task;
import com.petproject.minivns.entities.User;

import java.util.List;

public interface TaskService {
    Task create(Task task);

    List<Task> getAll();

    Task getById(Integer id);

    Task update(Task task);

    void delete(Integer id);


    List<Task> getAllByUser_id(Integer id);

    List<Task> getAllBySubject_id(Integer id);
}
