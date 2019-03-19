package lt.sventes.holidays.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.sventes.countries.Country;
import lt.sventes.countries.CountryData;
import lt.sventes.countries.service.CountryRepository;
import lt.sventes.holidays.CountryOfHoliday;
import lt.sventes.holidays.Holiday;
import lt.sventes.holidays.HolidayData;

@Service
public class HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;

	@Autowired
	CountryRepository countryRepository;

	// Visų švenčių nuskaitymas
	@Transactional(readOnly = true)
	public List<HolidayData> getFullListOfHolidays() {
		return holidayRepository.findAll().stream().map((holiday) -> new HolidayData(holiday.getTitle(),
				holiday.getDescription(), holiday.getImage(), holiday.getType(), holiday.isFlag()
		// holiday.getCountries()
		)).collect(Collectors.toList());
	}

	// Vienos šventės nuskaitymas
	@Transactional(readOnly = true)
	public HolidayData findHolidayByTitle(String title) {
		Holiday currentHoliday = holidayRepository.findHolidayByTitle(title);
		HolidayData holidayToController = new HolidayData(currentHoliday.getTitle(), currentHoliday.getDescription(),
				currentHoliday.getImage(), currentHoliday.getType(), currentHoliday.isFlag());
		return holidayToController;
	}

	// Naujos šventės sukūrimas
	@Transactional
	public void createHoliday(String title, String description, String image, String type, boolean flag,
			List<Country> countries) {
		Holiday newHoliday = new Holiday(title, description, image, type, flag, countries);
		holidayRepository.save(newHoliday);
	}

	// Esamos šventės duomenų pakeitimas
	@Transactional
	public void updateHoliday(String currentTitle, String title, String description, String image, String type,
			boolean flag) {
		Holiday holidayToUpdate = holidayRepository.findHolidayByTitle(currentTitle);
		holidayToUpdate.setTitle(title);
		holidayToUpdate.setDescription(description);
		holidayToUpdate.setImage(image);
		holidayToUpdate.setType(type);
		holidayToUpdate.setFlag(flag);
		holidayRepository.save(holidayToUpdate);
	}

	// Esamos šventės ištrynimas (metodas aprašytas Repositorijoje)
	@Transactional
	public void deleteHoliday(String title) {
		holidayRepository.deleteHolidayByTitle(title);
	}

	// Vienos šventės šalių nuskaitymas
	@Transactional(readOnly = true)
	public List<String> getHolidayCountries(String holidayTitle) {
		Holiday currentHoliday = holidayRepository.findHolidayByTitle(holidayTitle);

		return currentHoliday.getCountries().stream().map((country) -> country.getTitle()).collect(Collectors.toList());

	}

	// Šalių priskyrimas šventei
	@Transactional
	public void addCountryToHoliday(String title, List<String> countryList) {
		Holiday currentHoliday = holidayRepository.findHolidayByTitle(title);
		List<Country> alreadyAddedCountryList = currentHoliday.getCountries();
		List<String> alreadyAddedCountryStringList = alreadyAddedCountryList.stream()
				.map((country) -> country.getTitle()).collect(Collectors.toList());
		for (String country : countryList) {
			if (!alreadyAddedCountryStringList.contains(country)) {
				Country countryToAdd = countryRepository.findCountryByTitle(country);
				currentHoliday.addCountry(countryToAdd);
			}
		}
		holidayRepository.save(currentHoliday);
		return;
	}

	// Šalių pašalinimas iš šventės
	@Transactional
	public void removeCountryFromHoliday(String title, List<String> countryList) {

		Holiday currentHoliday = holidayRepository.findHolidayByTitle(title);
		List<Country> alreadyAddedCountryList = currentHoliday.getCountries();
		List<String> alreadyAddedCountryStringList = alreadyAddedCountryList.stream()
				.map((country) -> country.getTitle()).collect(Collectors.toList());
		for (String country : countryList) {
			if (alreadyAddedCountryStringList.contains(country)) {
				Country countryToRomove = countryRepository.findCountryByTitle(country);
				currentHoliday.removeCountry(countryToRomove);
			}
		}
		holidayRepository.save(currentHoliday);
	}
}
