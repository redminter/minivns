package com.petproject.minivns.service.impl;

import com.petproject.minivns.entities.Task;
import com.petproject.minivns.repositories.StateRepository;
import com.petproject.minivns.repositories.SubjectRepository;
import com.petproject.minivns.repositories.TaskRepository;
import com.petproject.minivns.repositories.UserRepository;
import com.petproject.minivns.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final StateRepository stateRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, StateRepository stateRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
        this.taskRepository = taskRepository;
        this.stateRepository = stateRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getById(Integer id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task id not found")
        );
    }

    @Override
    public Task update(Task task) {
        List<Integer> ids = getAll().stream()
                .map(Task::getId).collect(Collectors.toList());
        if (task != null && ids.contains(task.getId())) {
            getById(task.getId()).setLink(task.getLink());
            getById(task.getId()).setDeadline(task.getDeadline());
            getById(task.getId()).setTitle(task.getTitle());
            task.setStateByStateId(getById(task.getId()).getStateByStateId());
            task.setUser_id(getById(task.getId()).getUser_id());
            task.setSubjectBySubjectId(getById(task.getId()).getSubjectBySubjectId());
            return taskRepository.save(task);
        }
        throw new RuntimeException("Task is null or not found and cannot be updated");
    }
    @Override
    public void delete(Integer id) {
        Task task = getById(id);
        if(task != null && getAll().contains(task)) {
            taskRepository.delete(task);
        }
        else{
            throw new RuntimeException("Task is null or not found and cannot be deleted");
        }
    }
}
