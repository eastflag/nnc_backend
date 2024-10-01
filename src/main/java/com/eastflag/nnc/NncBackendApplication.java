package com.eastflag.nnc;

import com.eastflag.nnc.auth.service.AuthenticationService;
import com.eastflag.nnc.auth.dto.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.eastflag.nnc.user.model.Role.*;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class NncBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NncBackendApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.nickname("admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("token: " + service.signup(admin).getData().toString());

			var manager = RegisterRequest.builder()
					.nickname("manager")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("token: " + service.signup(manager).getData().toString());

			var user1 = RegisterRequest.builder()
					.nickname("user1")
					.email("user1@mail.com")
					.password("password")
					.role(USER)
					.build();
			System.out.println("token: " + service.signup(user1).getData().toString());

		};
	}*/

}
