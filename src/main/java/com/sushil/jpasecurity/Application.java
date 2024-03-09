package com.sushil.jpasecurity;

import com.sushil.jpasecurity.model.Post;
import com.sushil.jpasecurity.model.User;
import com.sushil.jpasecurity.repository.PostRepository;
import com.sushil.jpasecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostRepository posts, UserRepository users, PasswordEncoder encoder) {
		return args -> {
//			users.save(new User("user","password","ROLE_USER"));
//			users.save(new User("admin","password","ROLE_USER,ROLE_ADMIN"));
			users.save(new User("user",encoder.encode("password"),"ROLE_USER"));
			users.save(new User("admin",encoder.encode("password"),"ROLE_USER,ROLE_ADMIN"));
			posts.save(new Post("Hello World!", "hello-world", "Welcome to my blog"));
		};
	}
}
