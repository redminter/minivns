package com.petproject.minivns.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.petproject.minivns.entities.Subject;
import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubjectResponse {
    Integer id;
    String title;
    String vns_url;
    String pract_url;
    Integer credit;
    String lab_url;
    String lection_url;

    boolean at_monday;
    boolean at_tuesday;
    boolean at_wednesday;
    boolean at_thursday;
    boolean at_friday;

    public SubjectResponse(Subject subject) {
        this.id = subject.getId();
        this.title = subject.getTitle();
        this.vns_url = subject.getVnsUrl();
        this.pract_url = subject.getPraktUrl();
        this.lab_url = subject.getLabUrl();
        this.lection_url = subject.getLectionUrl();
        this.credit=subject.getCredit();
        this.at_monday = subject.isAtMonday();
        this.at_tuesday = subject.isAtTuesday();
        this.at_wednesday = subject.isAtWednesday();
        this.at_thursday = subject.isAtThursday();
        this.at_friday = subject.isAtFriday();
    }
}
