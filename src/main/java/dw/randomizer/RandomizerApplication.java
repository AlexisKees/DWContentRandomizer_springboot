package dw.randomizer;

import dw.randomizer.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RandomizerApplication implements CommandLineRunner {

	@Autowired
	private IAreaDangerCRUDService areaDangerService;
	@Autowired
	private IAreaDiscoveryCRUDService areaDiscoveryService;
	@Autowired
	private IAreaCRUDService areaService;
	@Autowired
	private IBiomeCRUDService biomeService;
	@Autowired
	private ICreatureCRUDService creatureService;
	@Autowired
	private IDangerCRUDService dangerService;
	@Autowired
	private IDiscoveryCRUDService discoveryService;
	@Autowired
	private IDungeonCRUDService dungeonService;
	@Autowired
	private IFollowerCRUDService followerService;
	@Autowired
	private INPCCRUDService inpcService;
	@Autowired
	private IQuestCRUDService questService;
	@Autowired
	private ISteadingCRUDService steadingService;


	private static final Logger logger = LoggerFactory.getLogger(RandomizerApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando la aplicación...");

		//esta linea levanta la fábrica de Spring
		SpringApplication.run(RandomizerApplication.class, args);

		logger.info("Finalizada.");
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
