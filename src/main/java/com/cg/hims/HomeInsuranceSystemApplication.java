package com.cg.hims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages= {"com.cg.hims.entities"})
@ComponentScan(basePackages= {"com.cg.hims.controllers","com.cg.hims.service","com.cg.hims.repository","package com.cg.hims.user.controller"})
public class HomeInsuranceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeInsuranceSystemApplication.class, args);
		System.out.println("Server Started....");
	}

}
