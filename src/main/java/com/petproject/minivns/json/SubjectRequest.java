package com.petproject.minivns.json;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SubjectRequest {
    @NotBlank(message = "The title of subject cannot be empty")
    String title;

    String vns_url;
    String pract_url;
    String lab_url;
    String lection_url;
    Integer credit;
    boolean at_monday;
    boolean at_tuesday;
    boolean at_wednesday;
    boolean at_thursday;
    boolean at_friday;
}
