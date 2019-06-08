package lt.sventes.countries.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import lt.sventes.countries.model.Country;
import lt.sventes.countries.model.CountryData;
import lt.sventes.security.payload.ApiResponse;

@Service
@Slf4j
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
	public ResponseEntity<?> createCountry(String title, String image, String president) {
		// Patikrinu ar tokiu pavadinimu šalis jau egzistuoja
		if (countryRepository.existsByTitle(title)) {
			return new ResponseEntity<>(new ApiResponse(false, "Country with such title already exists"),
					HttpStatus.BAD_REQUEST);
		}

		// 1.Sugeneruoju atsitiktinę eilutę iš 7 simbolių
		String countryCode = RandomStringUtils.random(7, true, true) + "_" + title.replaceAll("\\s+", "");
		Country newCountry = new Country(countryCode, title, image, president);
		Country result = countryRepository.save(newCountry);
		log.info("A new country (" + title + ") has been created");
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/countries/{countryCode}")
				.buildAndExpand(result.getCountryCode()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "Country created successfully"));
	}

	// Esamos šalies duomenų pakeitimas
	@Transactional
	public void updateCountry(String countryCode, String title, String image, String president) {
		
		Country countryToUpdate = countryRepository.findCountryByCountryCode(countryCode);
		countryToUpdate.setTitle(title);
		countryToUpdate.setImage(image);
		countryToUpdate.setPresident(president);
		countryRepository.save(countryToUpdate);
		log.info("Country (" + title + ") has been updated");
	}

	// Esamos šalies ištrynimas (metodas aprašytas Repositorijoje)
	@Transactional
	public void deleteCountry(String countryCode) {
		Country countryToDelete = countryRepository.findCountryByCountryCode(countryCode);
		countryRepository.deleteCountryByCountryCode(countryCode);
		log.info("Country (" + countryToDelete.getTitle() + ") has been deleted");
	}
	
	// Vienos šalies švenčių nuskaitymas
		@Transactional(readOnly = true)
		public List<String> getCountryHolidays(String countryCode) {
			Country currentCountry = countryRepository.findCountryByCountryCode(countryCode);
			
			return currentCountry.getHolidays().stream().map((holiday) -> holiday.getTitle()).collect(Collectors.toList());

		}

}
