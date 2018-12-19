package it.akademija;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class TestasService {
	
	@Autowired
	TestasRepository testasRepository;
	
	public List<Testas> getTestas (){
		return testasRepository.findAll();
		
	}
	
	public void createTestas(long id) {
		Testas newTestas = new Testas();
		testasRepository.save(newTestas);
	}
}
