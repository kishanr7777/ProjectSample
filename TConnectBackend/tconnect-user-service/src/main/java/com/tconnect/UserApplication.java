package com.tconnect;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(UserApplication.class, args);
	}
}

