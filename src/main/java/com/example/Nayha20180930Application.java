package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Nayha20180930Application {

	public static void main(String[] args) {
		SpringApplication.run(Nayha20180930Application.class, args);
	}
}
