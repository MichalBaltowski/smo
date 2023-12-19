package pl.playwithme.smo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.playwithme.smo.Security.JwtGenerator;

@SpringBootApplication
public class SmoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SmoApplication.class, args);
	}

}
