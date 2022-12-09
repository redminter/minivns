package com.petproject.minivns.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.petproject.minivns.entities.State;
import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.entities.Task;
import com.petproject.minivns.entities.User;
import lombok.Value;


import java.time.LocalDateTime;

@Value
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TaskResponse {

     Integer id;
     String title;

     String link;

     LocalDateTime deadline;
    @JsonProperty("subject_by_subject_id")
     Subject subjectBySubjectId;
    @JsonProperty("state_by_state_id")
     State stateByStateId;

     User user;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.link = task.getLink();
        this.deadline = task.getDeadline();
        this.subjectBySubjectId = task.getSubjectBySubjectId();
        this.stateByStateId = task.getStateByStateId();
        this.user = task.getUser();
    }
}