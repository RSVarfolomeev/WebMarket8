package ru.geekbrains.aprilmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@PropertySource(value = "private.properties", ignoreResourceNotFound = true)
@SpringBootApplication
public class AprilMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(AprilMarketApplication.class, args);
	}

}
