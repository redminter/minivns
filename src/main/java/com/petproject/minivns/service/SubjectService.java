package com.petproject.minivns.service;

import com.petproject.minivns.entities.Subject;

import java.time.LocalDateTime;
import java.util.List;

public interface SubjectService {
    Subject create(Subject subject);

    List<Subject> getAll();
    List<Subject> getSchedule(LocalDateTime localDateTime);

    Subject getById(Integer id);

    //todo implement updating certain field
    Subject update(Subject subject);

    void delete(Integer id);
}
