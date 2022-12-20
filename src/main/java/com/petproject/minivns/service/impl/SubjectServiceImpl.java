package com.petproject.minivns.service.impl;

import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.repositories.SubjectRepository;
import com.petproject.minivns.service.SubjectService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                () -> new RuntimeException("Subject id not found")
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
        if (subject != null && ids.contains(subject.getId())) {
            getById(subject.getId()).setTitle(subject.getTitle());
            getById(subject.getId()).setLabUrl(subject.getLabUrl());
            getById(subject.getId()).setLectionUrl(subject.getLectionUrl());
            getById(subject.getId()).setPraktUrl(subject.getPraktUrl());
            getById(subject.getId()).setVnsUrl(subject.getVnsUrl());

            return subjectRepository.save(subject);
        }
        throw new RuntimeException("Subject is null or not found and cannot be updated");
    }
    @Override
    public void delete(Integer id) {
        Subject subject = getById(id);
        if(subject != null && getAll().contains(subject)) {
            subjectRepository.delete(subject);
        }
        else{
            throw new RuntimeException("Subject is null or not found and cannot be deleted");
        }
    }
}
