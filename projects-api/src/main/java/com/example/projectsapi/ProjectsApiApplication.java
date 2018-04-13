package com.example.projectsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class ProjectsApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectsApiApplication.class, args);
	}
}
