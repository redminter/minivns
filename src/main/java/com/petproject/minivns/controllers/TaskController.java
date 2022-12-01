package com.petproject.minivns.controllers;

import com.petproject.minivns.entities.Task;
import com.petproject.minivns.json.TaskRequest;
import com.petproject.minivns.json.TaskResponse;
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
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("users/{user_id}/tasks")
public class TaskController {
    final TaskService taskService;
    final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    List<TaskResponse> getAll(@PathVariable("user_id") Integer user_id) {
        return taskService.getAllByUser_id(user_id).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable("user_id") Integer user_id, @Validated @RequestBody TaskRequest taskRequest, BindingResult result){
        if(result.hasErrors()){ throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data is bad entered");}
        Task newTask = new Task();

        newTask.setTitle(taskRequest.getTitle());
        newTask.setLink(taskRequest.getLink());
        newTask.setDeadline(taskRequest.getDeadline());
        newTask.setStateByStateId(taskRequest.getStateByStateId());
        newTask.setUser_id(userService.getById(user_id));
        newTask.setSubjectBySubjectId(taskRequest.getSubjectBySubjectId());
        taskService.create(newTask);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new TaskResponse(newTask) );
    }

    @GetMapping("/{task_id}")
    public TaskResponse getOne(@PathVariable("task_id") Integer taskId) {
        return new TaskResponse(taskService.getById(taskId));
    }

    @PutMapping({"/{task_id}"})
    @ResponseStatus ( HttpStatus.OK )
    public ResponseEntity<?> update(@PathVariable("user_id") Integer user_id, @PathVariable("task_id") Integer task_id, @Valid @RequestBody TaskRequest taskRequest) {
        Task task = taskService.getById(task_id);
        task.setTitle(taskRequest.getTitle());
        task.setLink(taskRequest.getLink());
        task.setDeadline(taskRequest.getDeadline());
        task.setStateByStateId(taskRequest.getStateByStateId());
        task.setUser_id(userService.getById(user_id));
        task.setSubjectBySubjectId(taskRequest.getSubjectBySubjectId());

        taskService.update(task);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{task_id}")
                .buildAndExpand(task.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new TaskResponse(task));
    }

    @DeleteMapping("/{task_id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    void delete (@PathVariable("task_id") Integer task_id) {
        taskService.delete(task_id);
    }
}
