package com.petproject.minivns.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.petproject.minivns.entities.State;
import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.entities.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class TaskRequest {

    @NotBlank(message = "The title of task cannot be empty")
    String title;

    String link;

    LocalDateTime deadline;
    @JsonProperty("subject_by_subject_id")
    Integer subjectBySubjectId;
    @JsonProperty("state_by_state_id")
    Integer stateByStateId;

//    User user_id;
}
