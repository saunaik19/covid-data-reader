package com.saurabh.example.coviddatareader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CovidDataReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidDataReaderApplication.class, args);
	}

}
