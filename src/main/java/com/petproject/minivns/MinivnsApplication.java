package com.petproject.minivns;


import com.petproject.minivns.repositories.SubjectRepository;
import com.petproject.minivns.repositories.TaskRepository;
import com.petproject.minivns.service.SubjectService;
import com.petproject.minivns.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@SpringBootApplication
public class MinivnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinivnsApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner run (SubjectService taskRepository){
//		return (args  ->{
//			System.out.println(taskRepository.getSchedule(LocalDateTime.now()));
//		});
//	}

}
