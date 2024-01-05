package com.example.UserLoginService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class UserLoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserLoginServiceApplication.class, args);
	}

}
