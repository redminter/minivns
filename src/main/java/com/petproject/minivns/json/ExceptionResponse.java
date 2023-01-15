package com.petproject.minivns.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
    private HttpStatus status;

    private int code;
    private LocalDateTime timestamp;

}
