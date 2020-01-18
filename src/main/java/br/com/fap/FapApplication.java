package br.com.fap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.util.TimeZone;
import javax.annotation.PostConstruct;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		FapApplication.class,
		Jsr310JpaConverters.class
})
public class FapApplication {
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(FapApplication.class, args);
	}

}
