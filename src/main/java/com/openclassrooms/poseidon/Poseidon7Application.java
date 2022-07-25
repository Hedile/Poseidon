package com.openclassrooms.poseidon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.openclassrooms.poseidon.*")
public class Poseidon7Application {

	public static void main(String[] args) {
		SpringApplication.run(Poseidon7Application.class, args);
	}

}
