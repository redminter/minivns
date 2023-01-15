package com.petproject.minivns.service.impl;

import com.petproject.minivns.entities.Task;
import com.petproject.minivns.entities.User;
import com.petproject.minivns.repositories.TaskRepository;
import com.petproject.minivns.service.TaskService;
import com.petproject.minivns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
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
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id " + id+ " is not found")
        );
    }

    @Override
    public Task update(Task task) {
        List<Integer> ids = getAll().stream()
                .map(Task::getId).toList();
        if(task == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Some information was typed wrong and task cannot be updated");
        }
        else if (!ids.contains(task.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id:"+ task.getId()+" is not found and cannot be updated");
        }
        else{
            getById(task.getId()).setLink(task.getLink());
            getById(task.getId()).setDeadline(task.getDeadline());
            getById(task.getId()).setTitle(task.getTitle());
            task.setIsDone(getById(task.getId()).getIsDone());
            task.setUser(getById(task.getId()).getUser());
            task.setSubjectBySubjectId(getById(task.getId()).getSubjectBySubjectId());
            return taskRepository.save(task);
        }
    }
    @Override
    public void delete(Integer id) {
        Task task = getById(id);
        if(task == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no task to delete");
        }
        else if (!getAll().contains(task)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task is not found and cannot be deleted");
        }
        else{
            taskRepository.delete(task);
        }
    }

    @Override
    public List<Task> getAllByUser_id(Integer id) {
        User user = userService.getById(id);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user found with id " + id);
        }
        return taskRepository.getTasksByUser_id(id);
    }
    @Override
    public List<Task> getAllBySubject_id(Integer userId, Integer subjectId) {
        List<Task> list = taskRepository.getTasksByUser_id(userId).stream().filter(p -> p.getSubjectBySubjectId().getId().equals(subjectId)).toList();
        if (!(list.isEmpty())) {
            return list;
        }
        else{
            throw new  ResponseStatusException(HttpStatus.NO_CONTENT, "There is no tasks for this subjects");
        }
    }
    @Override
    public void changeState(Integer id){
        Task task = getById(id);
        if(task == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no task to change");
        }
        else if (!getAll().contains(task)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task is not found and cannot be changed");
        }
        else {
            task.setIsDone(!(task.getIsDone()));
            taskRepository.save(task);
        }
    }
}
