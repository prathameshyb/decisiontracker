package com.prathamesh.decisiontracker;

import com.prathamesh.decisiontracker.entities.User;
import com.prathamesh.decisiontracker.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DecisiontrackerApplication {

	 static void main(String[] args) {

        SpringApplication.run(DecisiontrackerApplication.class, args);

	}


}
