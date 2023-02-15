package com.petproject.minivns.repositories;


import com.petproject.minivns.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

     List<Subject> getAllByAtMondayIsTrue();
     List<Subject> getAllByAtTuesdayIsTrue();
     List<Subject> getAllByAtWednesdayIsTrue();
     List<Subject> getAllByAtThursdayIsTrue();
     List<Subject> getAllByAtFridayIsTrue();

}
