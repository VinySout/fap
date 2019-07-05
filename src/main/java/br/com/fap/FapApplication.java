package br.com.fap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FapApplication {

	public static void main(String[] args) {
		SpringApplication.run(FapApplication.class, args);
	}

}
