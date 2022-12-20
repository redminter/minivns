package com.petproject.minivns.json;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class SubjectRequest {
    @NotBlank(message = "The title of subject cannot be empty")
    String title;

    String vns_url;
    String pract_url;
    String lab_url;
    String lection_url;
    boolean atMonday;
    boolean atTuesday;
    boolean atWednesday;
    boolean atThursday;
    boolean atFriday;
}
