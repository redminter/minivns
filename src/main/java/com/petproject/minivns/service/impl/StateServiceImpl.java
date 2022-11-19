package com.petproject.minivns.service.impl;

import com.petproject.minivns.entities.State;
import com.petproject.minivns.repositories.StateRepository;
import com.petproject.minivns.service.StateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService {
    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State create(State state){
        return stateRepository.save(state);
    }

    @Override
    public List<State> getAll() {
        return stateRepository.findAll();
    }
    @Override
    public State getById(Integer id) {
        return stateRepository.findById(id).orElseThrow(
                () -> new RuntimeException("State id not found")
        );
    }
    @Override
    public State getByName(String name){
        State state = stateRepository.findByName(name);
        if(state != null)
            return state;
        else{
            throw new RuntimeException("State name not found");
        }
    }

    @Override
    public State update(State state) {
        List<Integer> ids = getAll().stream()
                .map(State::getId).collect(Collectors.toList());
        if (state != null && ids.contains(state.getId())) {
            getById(state.getId()).setName(state.getName());
            return stateRepository.save(state);
        }
        throw new RuntimeException("State is null or not found and cannot be updated");
    }
    @Override
    public void delete(Integer id) {
        State state = getById(id);
        if(state != null && getAll().contains(state)) {
            stateRepository.delete(state);
        }
        else{
            throw new RuntimeException("Role is null of not found and cannot be deleted");
        }
    }
}
