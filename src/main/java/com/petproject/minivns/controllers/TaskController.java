package com.petproject.minivns.controllers;

import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.entities.Task;
import com.petproject.minivns.json.TaskRequest;
import com.petproject.minivns.json.TaskResponse;
import com.petproject.minivns.service.SubjectService;
import com.petproject.minivns.service.TaskService;
import com.petproject.minivns.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("users/{user_id}/")
public class TaskController {
    final TaskService taskService;
    final UserService userService;
    final SubjectService subjectService;

    public TaskController(TaskService taskService, UserService userService, SubjectService subjectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.subjectService = subjectService;
    }
    //allow access for any admin and for user certain id
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER') and authentication.principal.id == #userId")
    @GetMapping("/tasks")
    List<TaskResponse> getAllByUser(@PathVariable("user_id") Integer userId) {
        userService.getById(userId);
        List<TaskResponse> list = taskService.getAllByUser_id(userId).stream()
                .map(TaskResponse::new)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There are no tasks for user " + userService.getById(userId).getFirstName() + " " + userService.getById(userId).getLastName());
        } else {
            return list;
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER') and authentication.principal.id == #userId")
    @GetMapping("subjects/{subject_id}/tasks")
    List<TaskResponse> getAllBySubject(@PathVariable("user_id") Integer userId, @PathVariable("subject_id") Integer subjectId) {
        userService.getById(userId);
        List<Subject> subjects = taskService.getAllByUser_id(userId).stream()
                .map(Task::getSubjectBySubjectId).toList();
        if (!subjects.contains(subjectService.getById(subjectId))) {
            return null;
        } else {
            List<TaskResponse> list = taskService.getAllBySubject_id(userId, subjectId).stream()
                    .map(TaskResponse::new)
                    .collect(Collectors.toList());
            if (list.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no task in this subject");
            } else {
                return list;
            }
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER') and authentication.principal.id == #userId")
    @PostMapping("subjects/{subject_id}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable("user_id") Integer userId, @PathVariable("subject_id") Integer subjectId, @Validated @RequestBody TaskRequest taskRequest, BindingResult result) {
        userService.getById(userId);
//        List<Subject> subjects = taskService.getAllByUser_id(userId).stream()
//                .map(Task::getSubjectBySubjectId).toList();
//        if (!subjects.contains(subjectService.getById(subjectId))) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tasks for user with id:" + userId + " in subject with id:" + subjectId);
//        } else
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data is bad entered");
        }
        Task newTask = new Task();

        newTask.setTitle(taskRequest.getTitle());
        if (!(taskRequest.getLink() == null)) {
            newTask.setLink(taskRequest.getLink());
        } else {
            newTask.setLink("");
        }
        if (!(taskRequest.getDeadline() == null)) {
            newTask.setDeadline(taskRequest.getDeadline());
        } else {
            newTask.setDeadline(LocalDateTime.of(1, 1, 1, 1, 1));
        }
        newTask.setIsDone(false);
        newTask.setUser(userService.getById(userId));
        newTask.setSubjectBySubjectId(subjectService.getById(subjectId));
        taskService.create(newTask);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new TaskResponse(newTask));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER') and authentication.principal.id == #userId")
    @GetMapping("subjects/{subject_id}/tasks/{task_id}")
    public TaskResponse getOne(@PathVariable("user_id") Integer userId, @PathVariable("subject_id") Integer subjectId, @PathVariable("task_id") Integer taskId) {
        userService.getById(userId);
        List<Subject> subjects = taskService.getAllByUser_id(userId).stream()
                .map(Task::getSubjectBySubjectId).toList();
        if (!subjects.contains(subjectService.getById(subjectId))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tasks for user with id:" + userId + " in subject with id:" + subjectId);
        } else {
            if (!(taskService.getById(taskId).getUser().getId().equals(userId)) && !userService.getById(userId).getRole_Id().getAuthority().equals("ROLE_ADMIN")) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can`t reach this task");
            } else {
                return new TaskResponse(taskService.getById(taskId));
            }
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER') and authentication.principal.id == #userId")
    @PutMapping({"subjects/{subject_id}/tasks/{task_id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable("user_id") Integer userId, @PathVariable("subject_id") Integer subjectId, @PathVariable("task_id") Integer taskId, @Valid @RequestBody TaskRequest taskRequest, BindingResult result) {
        List<Subject> subjects = taskService.getAllByUser_id(userId).stream()
                .map(Task::getSubjectBySubjectId).toList();
        if (!(taskService.getById(taskId).getUser().getId().equals(userId)) && !userService.getById(userId).getRole_Id().getAuthority().equals("ROLE_ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can`t reach this task");
        } else if (!subjects.contains(subjectService.getById(subjectId))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tasks for user with id:" + userId + " in subject with id:" + subjectId);
        } else if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data is bad entered");
        }
        Task updatedTask = taskService.getById(taskId);
        updatedTask.setTitle(taskRequest.getTitle());
        if (!(taskRequest.getLink() == null)) {
            updatedTask.setLink(taskRequest.getLink());
        } else {
            updatedTask.setLink("");
        }
        if (!(taskRequest.getMark() == null)) {
            updatedTask.setMark(taskRequest.getMark());
        }
        if (!(taskRequest.getMark() == null)) {
            updatedTask.setMaxMark(taskRequest.getMaxMark());
        }
        if (!(taskRequest.getDeadline() == null)) {
            updatedTask.setDeadline(taskRequest.getDeadline());
        } else {
            updatedTask.setDeadline(LocalDateTime.of(1, 1, 1, 1, 1));
        }
        if (!(taskRequest.getDoneDate() == null)) {
            updatedTask.setDoneDate(taskRequest.getDoneDate());
        }
        updatedTask.setIsDone(taskRequest.getIsDone());
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER') and authentication.principal.id == #userId")
    @DeleteMapping("subjects/{subject_id}/tasks/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("task_id") Integer taskId, @PathVariable("subject_id") Integer subjectId, @PathVariable("user_id") Integer userId) {
        userService.getById(userId);
        List<Subject> subjects = taskService.getAllByUser_id(userId).stream()
                .map(Task::getSubjectBySubjectId).toList();
        if (!subjects.contains(subjectService.getById(subjectId))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tasks for user with id:" + userId + " in subject with id:" + subjectId);
        } else if (!(taskService.getById(taskId).getUser().getId().equals(userId)) && !userService.getById(userId).getRole_Id().getAuthority().equals("ROLE_ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can`t reach this task");
        } else {
            taskService.delete(taskId);
        }
    }
}
