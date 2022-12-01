package com.petproject.minivns.json;

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

    Subject subjectBySubjectId;

    State stateByStateId;

    User user_id;
}
