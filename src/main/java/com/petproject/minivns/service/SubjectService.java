package com.petproject.minivns.service;

import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.entities.User;

import java.util.List;

public interface SubjectService {
    Subject addSubject(Subject subject);

    List<Subject> getAll();

    Subject getById(Integer id);

    //todo implement updating certain field
    Subject update(Subject subject);

    void delete(Integer id);
}
