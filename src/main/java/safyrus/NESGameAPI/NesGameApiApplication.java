package safyrus.NESGameAPI;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import safyrus.NESGameAPI.dao.NESGameDAOSimple;

@SpringBootApplication
public class NesGameApiApplication {

	public static void main(String[] args) throws FileNotFoundException {
		// create Application context
		ConfigurableApplicationContext context = SpringApplication.run(NesGameApiApplication.class, args);
		// init 'database'
		NESGameDAOSimple dao = context.getBean(NESGameDAOSimple.class);
		dao.init("nesgames.json");
	}

}
