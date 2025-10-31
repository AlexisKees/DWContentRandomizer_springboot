package dw.randomizer;

import dw.randomizer.service.IBiomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RandomizerApplication {

	@Autowired
	private IBiomeService biomeService;

	private static final Logger logger = LoggerFactory.getLogger(RandomizerApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando la aplicación...");

		//esta linea levanta la fábrica de Spring
		SpringApplication.run(RandomizerApplication.class, args);

		logger.info("Finalizada.");
	}

}
