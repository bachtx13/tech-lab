package com.sem4project.sem4;

import com.sem4project.sem4.repository.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class Sem4Application {

	public static void main(String[] args) {
		SpringApplication.run(Sem4Application.class, args);
	}

}
