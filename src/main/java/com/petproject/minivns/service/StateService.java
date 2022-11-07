package com.petproject.minivns.service;

import com.petproject.minivns.entities.State;

import java.util.List;

public interface StateService {
    State addState(State state);

    List<State> getAll();

    State getById(Integer id);

    State getByName(String name);

    State update(State state);

    void delete(Integer id);
}
