package com.petproject.minivns;

import com.petproject.minivns.entities.Subject;
import com.petproject.minivns.entities.Task;
import com.petproject.minivns.entities.User;
import com.petproject.minivns.service.StateService;
import com.petproject.minivns.service.SubjectService;
import com.petproject.minivns.service.TaskService;
import com.petproject.minivns.service.UserService;
import com.petproject.minivns.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class MinivnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinivnsApplication.class, args);
	}
	@Transactional
	@Bean
	public CommandLineRunner run (){
		return (args  ->{

		});
	}

}
