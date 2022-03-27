package gratis.curriculo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "gratis.curriculo", exclude = {SecurityAutoConfiguration.class})
@EntityScan(basePackages = "gratis.curriculo.models")
public class CurriculoGratisApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurriculoGratisApiApplication.class, args);
	}

}
