package com.petproject.minivns.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.petproject.minivns.entities.Subject;
import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubjectResponse {
     String title;
     String vns_url;
     String pract_url;

     String lab_url;
     String lection_url;

//     boolean at_monday;
//     boolean at_tuesday;
//     boolean at_wednesday;
//     boolean at_thursday;
//     boolean at_friday;
    public SubjectResponse(Subject subject) {
        this.title = subject.getTitle();
        this.vns_url = subject.getVnsUrl();
        this.pract_url = subject.getPraktUrl();
        this.lab_url = subject.getLabUrl();
        this.lection_url = subject.getLectionUrl();
//        at_monday = subject.isAtMonday();
//        at_tuesday = subject.isAtTuesday();
//        at_wednesday = subject.isAtWednesday();
//        at_thursday = subject.isAtThursday();
//        at_friday = subject.isAtFriday();
    }
}
