package com.myintrest.phasect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PhaseCtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhaseCtApplication.class, args);
	}

}
