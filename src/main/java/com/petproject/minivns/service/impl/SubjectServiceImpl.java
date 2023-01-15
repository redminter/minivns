package com.petproject.minivns.service.impl;

import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.repositories.SubjectRepository;
import com.petproject.minivns.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    @Override
    public Subject create(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }
    @Override
    public Subject getById(Integer id) {
        return subjectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject with id " +id+ " is not found")
        );
    }

    public List<Subject> getSchedule(LocalDateTime localDateTime)
    {
        return switch(localDateTime.getDayOfWeek()){
            case MONDAY -> subjectRepository.getAllByAtMondayIsTrue();
            case TUESDAY -> subjectRepository.getAllByAtTuesdayIsTrue();
            case WEDNESDAY -> subjectRepository.getAllByAtWednesdayIsTrue();
            case THURSDAY -> subjectRepository.getAllByAtThursdayIsTrue();
            case FRIDAY, SATURDAY, SUNDAY-> subjectRepository.getAllByAtFridayIsTrue();
        };
    }

    //todo implement updating certain field
    @Override
    public Subject update(Subject subject) {
        List<Integer> ids = getAll().stream()
                .map(Subject::getId).toList();
        if(subject == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Some information was typed wrong and subject cannot be updated");
        }
        else if (!ids.contains(subject.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject with id:"+ subject.getId()+ " not found and cannot be updated");
        }
        else {
            getById(subject.getId()).setTitle(subject.getTitle());
            getById(subject.getId()).setLabUrl(subject.getLabUrl());
            getById(subject.getId()).setLectionUrl(subject.getLectionUrl());
            getById(subject.getId()).setPraktUrl(subject.getPraktUrl());
            getById(subject.getId()).setVnsUrl(subject.getVnsUrl());

            return subjectRepository.save(subject);
        }
    }
    @Override
    public void delete(Integer id) {
        Subject subject = getById(id);
        if(subject == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no subject to delete");
        }
        else if (!getAll().contains(subject)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject is not found and cannot be deleted");
        }
       else{
            subjectRepository.delete(subject);
        }
    }
}
