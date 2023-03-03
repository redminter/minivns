package com.petproject.minivns.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.entities.Task;
import lombok.Value;


import java.time.LocalDateTime;

@Value
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TaskResponse {

     Integer id;
     String title;

     String link;

     LocalDateTime deadline;
    @JsonProperty("done_date")
    LocalDateTime doneDate;

    Integer mark;
    @JsonProperty("max_mark")
    Integer maxMark;
    @JsonProperty("subject_id")
    Subject subjectBySubjectId;
    @JsonProperty("is_done")
     Boolean isDone;

//     User user;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.link = task.getLink();
        this.deadline = task.getDeadline();
        this.doneDate=task.getDoneDate();
        this.subjectBySubjectId = task.getSubjectBySubjectId();
        this.isDone = task.getIsDone();
        this.mark= task.getMark();
        this.maxMark=task.getMaxMark();

    //        this.user = task.getUser();
    }
}
