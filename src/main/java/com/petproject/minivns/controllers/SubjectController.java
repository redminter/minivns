package com.petproject.minivns.controllers;

import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.json.SubjectRequest;
import com.petproject.minivns.json.SubjectResponse;
import com.petproject.minivns.service.SubjectService;
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
@RequestMapping("/subject")
public class SubjectController {

    final SubjectService subjectService;


    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<SubjectResponse> getAll(){
        return subjectService.getAll().stream().map(SubjectResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Validated @RequestBody SubjectRequest subjectRequest, BindingResult result){
        if(result.hasErrors()){ throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data is bad entered");}
            Subject newSubject = new Subject();
            newSubject.setTitle(subjectRequest.getTitle());
            if(!subjectRequest.getVns_url().equals("")){
            newSubject.setVnsUrl(subjectRequest.getVns_url());}
            if(!subjectRequest.getPract_url().equals("")){
            newSubject.setPraktUrl(subjectRequest.getPract_url());}
            if(!subjectRequest.getLection_url().equals("")){
            newSubject.setLectionUrl(subjectRequest.getLection_url());}
            if(!subjectRequest.getLab_url().equals("")){
            newSubject.setLabUrl(subjectRequest.getLab_url());}
            subjectService.create(newSubject);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new SubjectResponse(newSubject));
    }

    @GetMapping("/{subject_id}")
    public SubjectResponse getOne(@PathVariable("subject_id") Integer subjectId) {
        return new SubjectResponse(subjectService.getById(subjectId));
    }

    @PutMapping({"/{subject_id}"})
    @ResponseStatus ( HttpStatus.OK )
    public ResponseEntity<?> update(@PathVariable("subject_id") Integer subject_id, @Valid @RequestBody SubjectRequest subjectRequest) {
        Subject subject = subjectService.getById(subject_id);
        subject.setTitle(subjectRequest.getTitle());
        if(!subjectRequest.getVns_url().equals("")){
            subject.setVnsUrl(subjectRequest.getVns_url());}
        if(!subjectRequest.getPract_url().equals("")){
            subject.setPraktUrl(subjectRequest.getPract_url());}
        if(!subjectRequest.getLection_url().equals("")){
            subject.setLectionUrl(subjectRequest.getLection_url());}
        if(!subjectRequest.getLab_url().equals("")){
            subject.setLabUrl(subjectRequest.getLab_url());}

        subjectService.update(subject);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{subject_id}")
                .buildAndExpand(subject.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new SubjectResponse(subject));
    }

    @DeleteMapping("/{subject_id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    void delete (@PathVariable("subject_id") Integer subject_id) {
        subjectService.delete(subject_id);
    }
}
