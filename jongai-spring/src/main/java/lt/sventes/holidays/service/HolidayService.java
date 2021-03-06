package lt.sventes.holidays.service;

import java.net.URI;
import java.time.LocalDate;
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
import lt.sventes.countries.service.CountryRepository;
import lt.sventes.holidays.model.Holiday;
import lt.sventes.holidays.model.HolidayData;
import lt.sventes.holidays.model.HolidayDataWithDate;
import lt.sventes.holidays.model.HolidayTypeName;
import lt.sventes.security.payload.ApiResponse;

@Service
@Slf4j
public class HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;

	@Autowired
	private CountryRepository countryRepository;

	// Visų švenčių nuskaitymas
	@Transactional(readOnly = true)
	public List<HolidayData> getFullListOfHolidays() {
		return holidayRepository.findAll().stream()
				.map((holiday) -> 
				new HolidayData(holiday.getCode(),
								holiday.getTitle(),
								holiday.getDescription(),
								holiday.getImage(),
								holiday.getType(),
								holiday.isFlag()
								// holiday.getCountries()
				)).collect(Collectors.toList());
	}
	
	
	
	// Visų švenčių nuskaitymas su datos lauku
	@Transactional(readOnly = true)
	public List<HolidayDataWithDate> getFullListOfHolidaysWithDate() {
		return holidayRepository.findAll().stream()
				.map((holiday) -> 
				new HolidayDataWithDate(holiday.getCode(),
								holiday.getTitle(),
								holiday.getDescription(),
								holiday.getImage(),
								replaceUnderscoreWithDash(holiday.getType()),
								holiday.isFlag(),
								// holiday.getCountries()
								holiday.getSimpleDate()
				)).collect(Collectors.toList());
	}
		
	public String replaceUnderscoreWithDash(HolidayTypeName typeToModify) {
		String modifiedType = typeToModify.toString().replaceAll("_", "-");
		return modifiedType;
	}

	// Vienos šventės nuskaitymas
	@Transactional(readOnly = true)
	public HolidayDataWithDate findOneHolidayByCode(String code) {
		//Holiday currentHoliday = holidayRepository.findHolidayByTitle(title);
		Holiday currentHoliday = holidayRepository.findHolidayByCode(code);
		HolidayDataWithDate holidayToController = new HolidayDataWithDate(currentHoliday.getCode(),
				currentHoliday.getTitle(), currentHoliday.getDescription(),
				currentHoliday.getImage(), replaceUnderscoreWithDash(currentHoliday.getType()), currentHoliday.isFlag(),
				currentHoliday.getSimpleDate());
		return holidayToController;
	}

	// Naujos šventės sukūrimas
	/*@Transactional
	public ResponseEntity<?> createHoliday(String title, String description, String image, String type, boolean flag,
			List<Country> countries) {

		// Patikrinu ar tokiu pavadinimu šventė jau egzistuoja
		if (holidayRepository.existsByTitle(title)) {
			return new ResponseEntity<>(new ApiResponse(false, "Holiday with such title already exists"),
					HttpStatus.BAD_REQUEST);
		}

		//1.Sugeneruoju atsitiktinę eilutę iš 7 simbolių
		String code = RandomStringUtils.random(7, true, true);
		//2.Modifikuoju title, kad jame nebūtų tarpų
		String modifiedTitle = title.replaceAll("\\s+","");
		//3.Sugeneruoju galutinį lauką code
		code += "_" + modifiedTitle;
		Holiday newHoliday = new Holiday(code, title, description, image, type, flag, countries);
		Holiday result = holidayRepository.save(newHoliday);
		log.info("A new holiday (" + title + ") has been created");
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/holidays/{code}")
				.buildAndExpand(result.getCode()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "Holiday created successfully"));
	}*/
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Naujos šventės su DATA sukūrimas
	@Transactional
	public ResponseEntity<?> createHolidayWithDate(String title, String description, String image, String type, boolean flag,
			List<Country> countries, LocalDate simpleDate) {

		// Patikrinu ar tokiu pavadinimu šventė jau egzistuoja
		if (holidayRepository.existsByTitle(title)) {
			return new ResponseEntity<>(new ApiResponse(false, "Holiday with such title already exists"),
					HttpStatus.BAD_REQUEST);
		}
		String modifiedType = type.replaceAll("-", "_");
		//1.Sugeneruoju atsitiktinę eilutę iš 7 simbolių
		String code = RandomStringUtils.random(7, true, true);
		//2.Modifikuoju title, kad jame nebūtų tarpų
		String modifiedTitle = title.replaceAll("\\s+","");
		//3.Sugeneruoju galutinį lauką code
		code += "_" + modifiedTitle;
		Holiday newHoliday = new Holiday(code, title, description, image, HolidayTypeName.valueOf(modifiedType), flag, countries, simpleDate);
		Holiday result = holidayRepository.save(newHoliday);
		log.info("A new holiday (" + title + ") has been created");
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/holidays/{code}")
				.buildAndExpand(result.getCode()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "Holiday created successfully"));
	}
		
		////////////////////////////////////////////////////////////////////////////////////

	// Esamos šventės duomenų pakeitimas
	@Transactional
	public void updateHoliday(String code, String title, String description, String image, String type,
			boolean flag) {
		// Reikia gautą šventės tipą patikrinti ar jis turi savyje simbolių "-" ir juos pakeisti į "_"
		String modifiedType = type.replaceAll("-", "_");
		System.out.println("************************************************");
		System.out.println("************************************************");
		System.out.println("************************************************");
		System.out.println("ModifiedType = " + modifiedType);
		
		//Holiday holidayToUpdate = holidayRepository.findHolidayByTitle(currentTitle);
		Holiday holidayToUpdate = holidayRepository.findHolidayByCode(code);
		holidayToUpdate.setTitle(title);
		holidayToUpdate.setDescription(description);
		holidayToUpdate.setImage(image);
		holidayToUpdate.setType(HolidayTypeName.valueOf(modifiedType));
		holidayToUpdate.setFlag(flag);
		holidayRepository.save(holidayToUpdate);
		log.info("Holiday (" + title + ") has been updated");
	}

	// Esamos šventės ištrynimas (metodas aprašytas Repositorijoje)
	@Transactional
	public void deleteHoliday(String code) {
		//holidayRepository.deleteHolidayByTitle(title);
		Holiday holidayToDelete = holidayRepository.findHolidayByCode(code);
		holidayRepository.deleteHolidayByCode(code);
		log.info("Holiday (" + holidayToDelete.getTitle() + ") has been created");
	}

	// Vienos šventės šalių nuskaitymas
	@Transactional(readOnly = true)
	public List<String> getHolidayCountries(String code) {
		Holiday currentHoliday = holidayRepository.findHolidayByCode(code);
		//Holiday currentHoliday = holidayRepository.findHolidayByTitle(holidayTitle);

		return currentHoliday.getCountries().stream().map((country) -> country.getTitle()).collect(Collectors.toList());

	}

	// Šalių priskyrimas šventei
	@Transactional
	public void addCountryToHoliday(String code, List<String> countryList) {
		Holiday currentHoliday = holidayRepository.findHolidayByCode(code);
		List<Country> alreadyAddedCountryList = currentHoliday.getCountries();
		List<String> alreadyAddedCountryStringList = alreadyAddedCountryList.stream()
				.map((country) -> country.getTitle()).collect(Collectors.toList());
		for (String country : countryList) {
			if (!alreadyAddedCountryStringList.contains(country)) {
				Country countryToAdd = countryRepository.findCountryByTitle(country);
				currentHoliday.addCountry(countryToAdd);
				log.info("Country (" + countryToAdd.getTitle() + ") was added to holiday (" + currentHoliday.getTitle()
						+ ")");
			}
		}
		holidayRepository.save(currentHoliday);
		return;
	}

	// Šalių pašalinimas iš šventės
	@Transactional
	public void removeCountryFromHoliday(String code, List<String> countryList) {

		//Holiday currentHoliday = holidayRepository.findHolidayByTitle(title);
		Holiday currentHoliday = holidayRepository.findHolidayByCode(code);
		List<Country> alreadyAddedCountryList = currentHoliday.getCountries();
		List<String> alreadyAddedCountryStringList = alreadyAddedCountryList.stream()
				.map((country) -> country.getTitle()).collect(Collectors.toList());
		for (String country : countryList) {
			if (alreadyAddedCountryStringList.contains(country)) {
				Country countryToRemove = countryRepository.findCountryByTitle(country);
				currentHoliday.removeCountry(countryToRemove);
				log.info("Country (" + countryToRemove.getTitle() + ") was removed from holiday ("
						+ currentHoliday.getTitle() + ")");
			}
		}
		holidayRepository.save(currentHoliday);
	}
}
