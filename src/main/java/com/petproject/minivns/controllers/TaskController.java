package com.petproject.minivns.controllers;

import com.petproject.minivns.entities.Task;
import com.petproject.minivns.json.TaskRequest;
import com.petproject.minivns.json.TaskResponse;
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
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("users/{user_id}/")
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
    List<TaskResponse> getAllByUser(@PathVariable("user_id") Integer user_id) {
        return taskService.getAllByUser_id(user_id).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }
    @GetMapping("subjects/{subject_id}/tasks")
    List<TaskResponse> getAllBySubject(@PathVariable("user_id") Integer userId, @PathVariable("subject_id") Integer subjectId ) {
        return taskService.getAllBySubject_id(userId, subjectId).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable("user_id") Integer user_id, @Validated @RequestBody TaskRequest taskRequest, BindingResult result){
        if(result.hasErrors()){ throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data is bad entered");}
        Task newTask = new Task();

        newTask.setTitle(taskRequest.getTitle());
        newTask.setLink(taskRequest.getLink());
        newTask.setDeadline(taskRequest.getDeadline());
        newTask.setStateByStateId(stateService.getById(taskRequest.getStateByStateId()));
        newTask.setUser(userService.getById(user_id));
        newTask.setSubjectBySubjectId(subjectService.getById(taskRequest.getSubjectBySubjectId()));
        taskService.create(newTask);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new TaskResponse(newTask) );
    }

    @GetMapping("tasks/{task_id}")
    public TaskResponse getOne(@PathVariable("task_id") Integer taskId) {
        return new TaskResponse(taskService.getById(taskId));
    }

    @PutMapping({"tasks/{task_id}"})
    @ResponseStatus ( HttpStatus.OK )
    public ResponseEntity<?> update(@PathVariable("user_id") Integer user_id, @PathVariable("task_id") Integer task_id, @Valid @RequestBody TaskRequest taskRequest) {
        Task task = taskService.getById(task_id);
        task.setTitle(taskRequest.getTitle());
        task.setLink(taskRequest.getLink());
        task.setDeadline(taskRequest.getDeadline());
        task.setStateByStateId(stateService.getById(taskRequest.getStateByStateId()));
        task.setUser(userService.getById(user_id));
        task.setSubjectBySubjectId(subjectService.getById(taskRequest.getSubjectBySubjectId()));

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

    @DeleteMapping("tasks/{task_id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    void delete (@PathVariable("task_id") Integer task_id) {
        taskService.delete(task_id);
    }
}
