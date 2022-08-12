package com.assignment.dbclm;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "DBCLM API", version = "1.0", description = "NACE Information - Assignment"))
public class DbclmApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(DbclmApplication.class, args);
	}

}
