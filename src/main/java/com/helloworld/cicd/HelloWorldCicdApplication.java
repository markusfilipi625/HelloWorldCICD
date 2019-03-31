package com.helloworld.cicd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.helloworld.cicd")
public class HelloWorldCicdApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldCicdApplication.class, args);
	}

}
