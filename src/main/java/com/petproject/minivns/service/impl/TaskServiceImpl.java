package com.petproject.minivns.service.impl;

import com.petproject.minivns.entities.Task;
import com.petproject.minivns.repositories.TaskRepository;
import com.petproject.minivns.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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
                .map(Task::getId).toList();
        if (task != null && ids.contains(task.getId())) {
            getById(task.getId()).setLink(task.getLink());
            getById(task.getId()).setDeadline(task.getDeadline());
            getById(task.getId()).setTitle(task.getTitle());
            task.setIsDone(getById(task.getId()).getIsDone());
            task.setUser(getById(task.getId()).getUser());
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

    @Override
    public List<Task> getAllByUser_id(Integer id) {
        return taskRepository.getTasksByUser_id(id);
    }
    @Override
    public List<Task> getAllBySubject_id(Integer userId, Integer subjectId) {
        List<Task> list = taskRepository.getTasksByUser_id(userId).stream().filter(p -> p.getSubjectBySubjectId().getId().equals(subjectId)).toList();
        if (!(list.isEmpty())) {
            return list;
        }
        else{
            throw new RuntimeException();
        }
    }
    @Override
    public void changeState(Integer id){
        Task task = getById(id);
        if(task != null && getAll().contains(task)) {
            task.setIsDone(!(task.getIsDone()));
            taskRepository.save(task);
        }
        else{
            throw new RuntimeException("There is no task with id " + id);
        }
    }
}
