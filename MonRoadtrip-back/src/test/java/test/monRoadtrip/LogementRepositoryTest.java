package test.monRoadtrip;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import monRoadtrip.config.AppConfig;
import monRoadtrip.model.Adresse;
import monRoadtrip.model.Client;
import monRoadtrip.model.Hote;
import monRoadtrip.model.Logement;
import monRoadtrip.repositories.LogementRepository;
import monRoadtrip.services.CompteService;
import monRoadtrip.services.LogementService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
class LogementRepositoryTest {
	
	@Autowired
	LogementRepository logementRepository;
	
	@Autowired
	LogementService logementService;
	
	@Autowired
	CompteService compteService;

	@Test
	@Transactional
	void insertTest() {
		
		
		Adresse adresse = new Adresse("13","rue des peupliers","44000","Nantes");
		Hote hote = new Hote("Pierson","Robin2","robin@gmail.com","hote",LocalDate.parse("1997-03-17"));
		Hote hote2 = new Hote("Pierson","Robin3","robin@gmail.com","hote",LocalDate.parse("1997-03-17"));

		Client client = new Client("Test","test","test","test",LocalDate.parse("1996-12-05"));
		compteService.save(hote);
		compteService.save(client);
		compteService.save(hote2);
		System.out.println(hote.getId());
		
		Logement logement = new Logement(LocalDate.parse("2022-07-26"), 100, adresse, 0, hote);
		
		logementService.save(logement);
	}
	
	@Test
	@Transactional
	void insertServiceTest() {
		
		Adresse adresse = new Adresse("13","rue des peupliers","44000","Nantes");
		Hote hote = new Hote("Pierson","Robin","robin@gmail.com","hote",LocalDate.parse("1997-03-17"));
		Logement logement = new Logement(LocalDate.parse("2022-07-26"), 100, adresse, 0, hote);
		
		logementService.save(logement);
	}
	
	@Test
	@Transactional
	void deleteServiceTest() {
		
		Adresse adresse = new Adresse("13","rue des peupliers","44000","Nantes");
		Hote hote = new Hote("Pierson","Robin","robin@gmail.com","hote",LocalDate.parse("1997-03-17"));
		Logement logement = new Logement(LocalDate.parse("2022-07-26"), 100, adresse, 0, hote);
		
		logementService.save(logement);
		
		logementService.delete(logement);
	}
	
	@Test
	void testGetAll() {
		List<Logement> logements = logementService.getAll();
		System.out.println(logements);
	}

}
