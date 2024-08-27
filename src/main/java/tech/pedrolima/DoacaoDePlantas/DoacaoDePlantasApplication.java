package tech.pedrolima.DoacaoDePlantas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class DoacaoDePlantasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoacaoDePlantasApplication.class, args);
	}

}

