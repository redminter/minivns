package com.petproject.minivns;

import com.petproject.minivns.entities.Role;
import com.petproject.minivns.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MinivnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinivnsApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner run (RoleRepository roleRepository){
//		return (args  ->{
//			Role role = new Role("all");
//			roleRepository.save(role);
//			System.out.println(roleRepository.findAll());
//		});
//	}

}
