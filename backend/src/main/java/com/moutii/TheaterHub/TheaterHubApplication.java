package com.moutii.TheaterHub;

import com.moutii.TheaterHub.role.Role;
import com.moutii.TheaterHub.role.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class TheaterHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheaterHubApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(final RoleRepo roleRepo) {
		return args -> {
			final Optional<Role> userRole = roleRepo.findByName("USER");
			if(userRole.isEmpty()) {
				final Role role = new Role();
				role.setName("USER");
				role.setCreatedBy("APP");
				roleRepo.save(role);
			}
		};
	}

}
