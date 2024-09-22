package com.example.forex_data_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ForexDataSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexDataSchedulerApplication.class, args);
	}

}

// http://localhost:8080/h2-console/