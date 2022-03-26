package com.mathfr.poketodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PoketodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoketodoApplication.class, args);
	}

}
