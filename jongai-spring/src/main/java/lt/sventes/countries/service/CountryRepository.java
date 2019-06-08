package lt.sventes.countries.service;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.sventes.countries.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
	
	Country findCountryByCountryCode(String countryCode);
	void deleteCountryByCountryCode(String countryCode);
	
	//Šitas metodas naudojamas, kad surasti šalį pagal pavadinimą,
	//kai vartotojas iš galimų šalių renkasi, kokias šalis priskirti šventei
	Country findCountryByTitle(String title);
	boolean existsByTitle(String title);
}
