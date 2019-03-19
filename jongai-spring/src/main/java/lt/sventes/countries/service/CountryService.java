package lt.sventes.countries.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.sventes.countries.Country;
import lt.sventes.countries.CountryData;
import lt.sventes.holidays.Holiday;
import lt.sventes.holidays.HolidayData;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	// Šalių nuskaitymas
	@Transactional(readOnly = true)
	public List<CountryData> getFullListOfCountries() {
		return countryRepository.findAll().stream().map((country) -> new CountryData(country.getCountryCode(),
				country.getTitle(), country.getImage(), country.getPresident())).collect(Collectors.toList());
	}

	// Vienos šalies nuskaitymas
	@Transactional(readOnly = true)
	public CountryData findCountryByCountryCode(String countryCode) {
		Country currentCountry = countryRepository.findCountryByCountryCode(countryCode);
		CountryData countryToController = new CountryData(currentCountry.getCountryCode(), currentCountry.getTitle(),
				currentCountry.getImage(), currentCountry.getPresident());
		return countryToController;
	}

	// Naujos šalies sukūrimas
	@Transactional
	public void createCountry(String title, String image, String president) {
		// 1.Sugeneruoju atsitiktinę eilutę iš 7 simbolių
		String countryCode = RandomStringUtils.random(7, true, true) + title.replaceAll("\\s+", "");
		Country newCountry = new Country(countryCode, title, image, president);
		countryRepository.save(newCountry);
	}

	// Esamos šalies duomenų pakeitimas
	@Transactional
	public void updateCountry(String countryCode, String title, String image, String president) {
		
		Country countryToUpdate = countryRepository.findCountryByCountryCode(countryCode);
		countryToUpdate.setTitle(title);
		countryToUpdate.setImage(image);
		countryToUpdate.setPresident(president);
		countryRepository.save(countryToUpdate);
	}

	// Esamos šalies ištrynimas (metodas aprašytas Repositorijoje)
	@Transactional
	public void deleteCountry(String countryCode) {
		countryRepository.deleteCountryByCountryCode(countryCode);
		//countryRepository.deleteCountryByTitle(countryCode);
	}
}
