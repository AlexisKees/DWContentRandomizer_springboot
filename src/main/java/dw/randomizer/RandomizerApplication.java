package dw.randomizer;

import dw.randomizer.presentation.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RandomizerApplication implements CommandLineRunner {

	@Autowired
	private Main mainCLI;

	private static final Logger logger = LoggerFactory.getLogger(RandomizerApplication.class);

	public static void main(String[] args) {
		//esta linea levanta la fábrica de Spring
		SpringApplication.run(RandomizerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainCLI.run();
	}


}
