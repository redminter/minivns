package com.petproject.minivns;


import com.petproject.minivns.repositories.TaskRepository;
import com.petproject.minivns.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@SpringBootApplication
public class MinivnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinivnsApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner run (TaskService taskRepository){
//		return (args  ->{
//			System.out.println(taskRepository.getAllByUser_id(5));
//			System.out.println(taskRepository.getAllBySubject_id(5, 7));
//		});
//	}

}
