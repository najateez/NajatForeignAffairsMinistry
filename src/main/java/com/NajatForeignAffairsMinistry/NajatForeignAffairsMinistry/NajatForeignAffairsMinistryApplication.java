package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity //-> for security
@EnableGlobalMethodSecurity(prePostEnabled = true) //-> for security
@SpringBootApplication
public class NajatForeignAffairsMinistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NajatForeignAffairsMinistryApplication.class, args);
	}

}
