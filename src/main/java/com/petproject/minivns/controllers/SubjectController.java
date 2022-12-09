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
@RequestMapping("users/{users_id}/subjects")
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
        if(result.hasErrors()){ throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data(maybe title) is bad entered");}
            Subject newSubject = new Subject();
            newSubject.setTitle(subjectRequest.getTitle());
            if(!(subjectRequest.getVns_url()==null)){
            newSubject.setVnsUrl(subjectRequest.getVns_url());}
            else{
                newSubject.setVnsUrl("");}
            if(!(subjectRequest.getPract_url()==null)){
            newSubject.setPraktUrl(subjectRequest.getPract_url());}
            else{
                newSubject.setPraktUrl("");}
            if(!(subjectRequest.getLection_url()==null)){
            newSubject.setLectionUrl(subjectRequest.getLection_url());}
            else{
                newSubject.setLectionUrl("");}
            if(!(subjectRequest.getLab_url()==null)){
            newSubject.setLabUrl(subjectRequest.getLab_url());}
            else{
                newSubject.setLabUrl("");}
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
     try{
        return new SubjectResponse(subjectService.getById(subjectId));
    }catch (RuntimeException e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no subject with that id");
    }
    }

    @PutMapping({"/{subject_id}"})
    @ResponseStatus ( HttpStatus.OK )
    public ResponseEntity<?> update(@PathVariable("subject_id") Integer subjectId, @Valid @RequestBody SubjectRequest subjectRequest, BindingResult result) {
        try {
            subjectService.getById(subjectId);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no subject with that id");
        }
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some data(maybe title) is bad entered");
        }
        Subject updatedSubject = subjectService.getById(subjectId);
        updatedSubject.setTitle(subjectRequest.getTitle());
        if(!(subjectRequest.getVns_url()==null)){
            updatedSubject.setVnsUrl(subjectRequest.getVns_url());}
        if(!(subjectRequest.getPract_url()==null)){
            updatedSubject.setPraktUrl(subjectRequest.getPract_url());}
        if(!(subjectRequest.getLection_url()==null)){
            updatedSubject.setLectionUrl(subjectRequest.getLection_url());}
        if(!(subjectRequest.getLab_url()==null)){
            updatedSubject.setLabUrl(subjectRequest.getLab_url());}

        subjectService.update(updatedSubject);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{subject_id}")
                .buildAndExpand(updatedSubject.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(new SubjectResponse(updatedSubject));
    }

    @DeleteMapping("/{subject_id}")
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    void delete (@PathVariable("subject_id") Integer subject_id) {
        try{
        subjectService.delete(subject_id);
    }catch(RuntimeException e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no subject with that id");
    }
    }
}
