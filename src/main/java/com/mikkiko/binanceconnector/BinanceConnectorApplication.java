package com.mikkiko.binanceconnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BinanceConnectorApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(BinanceConnectorApplication.class);
		springApplication.setWebApplicationType(WebApplicationType.NONE);
		springApplication.run(args);
	}

}
