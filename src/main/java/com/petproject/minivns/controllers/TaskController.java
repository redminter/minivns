package com.petproject.minivns.controllers;

import com.petproject.minivns.entities.Task;
import com.petproject.minivns.json.TaskRequest;
import com.petproject.minivns.json.TaskResponse;
import com.petproject.minivns.json.UserResponse;
import com.petproject.minivns.service.StateService;
import com.petproject.minivns.service.SubjectService;
import com.petproject.minivns.service.TaskService;
import com.petproject.minivns.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("users/{user_id}/subjects/{subject_id}")
public class TaskController {
    final TaskService taskService;
    final UserService userService;
    final StateService stateService;
    final SubjectService subjectService;

    public TaskController(TaskService taskService, UserService userService, StateService stateService, SubjectService subjectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.stateService = stateService;
        this.subjectService = subjectService;
    }

    @GetMapping("/tasks")
    List<TaskResponse> getAllByUser(@PathVariable("user_id") Integer userId) {
        try {
            userService.getById(userId);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user with that id");
        }
        return taskService.getAllByUser_id(userId).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/tasks")
    List<TaskResponse> getAllBySubject(@PathVariable("user_id") Integer userId, @PathVariable("subject_id") Integer subjectId ) {
        try {
            userService.getById(userId);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user with that id");
        }try {
            subjectService.getById(subjectId);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user with that id");
        }
        return taskService.getAllBySubject_id(userId, subjectId).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable("user_id") Integer userId,@PathVariable("user_id") Integer subjectId, @Validated @RequestBody TaskRequest taskRequest, BindingResult result){
        if(result.hasErrors()){ throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data is bad entered");}
        Task newTask = new Task();

        newTask.setTitle(taskRequest.getTitle());
        if(!(taskRequest.getLink()==null)) {
        newTask.setLink(taskRequest.getLink());}
        else{
            newTask.setLink("");
        }
        if(!(taskRequest.getDeadline()==null)){
        newTask.setDeadline(taskRequest.getDeadline());}
        else{
            newTask.setDeadline(LocalDateTime.of(0, 0, 0, 0, 0));
        }
        newTask.setStateByStateId(stateService.getById(5));
        newTask.setUser(userService.getById(userId));
        newTask.setSubjectBySubjectId(subjectService.getById(subjectId));
        taskService.create(newTask);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new TaskResponse(newTask) );
    }

    @GetMapping("/tasks/{task_id}")
    public TaskResponse getOne(@PathVariable("task_id") Integer taskId) {
        try {
            return new TaskResponse(taskService.getById(taskId));
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no task with that id");
        }
    }

    @PutMapping({"/tasks/{task_id}"})
    @ResponseStatus ( HttpStatus.OK )
    public ResponseEntity<?> update(@PathVariable("user_id") Integer userId,@PathVariable("subject_id") Integer subjectId, @PathVariable("task_id") Integer taskId, @Valid @RequestBody TaskRequest taskRequest, BindingResult result) {
        try {
            taskService.getById(taskId);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no task with that id");
        }
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data is bad entered");
        }
        Task updatedTask = taskService.getById(taskId);
        updatedTask.setTitle(taskRequest.getTitle());
        if(!(taskRequest.getLink()==null)) {
            updatedTask.setLink(taskRequest.getLink());}
        else{
            updatedTask.setLink("");
        }
        if(!(taskRequest.getDeadline()==null)){
            updatedTask.setDeadline(taskRequest.getDeadline());}
        else{
            updatedTask.setDeadline(LocalDateTime.of(0, 0, 0, 0, 0));
        }
        updatedTask.setStateByStateId(stateService.getById(5));
        updatedTask.setUser(userService.getById(userId));
        updatedTask.setSubjectBySubjectId(subjectService.getById(subjectId));
        taskService.update(updatedTask);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{task_id}")
                .buildAndExpand(updatedTask.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new TaskResponse(updatedTask));
    }

    @DeleteMapping("/tasks/{task_id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    void delete (@PathVariable("task_id") Integer task_id) {
        try {
            taskService.delete(task_id);
        }catch(RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user with that id");
        }
    }
}
