package com.avnthe.vidly;

import com.avnthe.vidly.model.ApplicationUser;
import com.avnthe.vidly.repository.RoleRepository;
import com.avnthe.vidly.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class VidlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(VidlyApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository){
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;;

			Role adminRole = roleRepository.save(new Role(authority:"ADMIN"));
			roleRepository.save(new Role(authority:"USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), roles);
			userRepository.save(admin);
		};
	}

}
