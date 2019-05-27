package lt.sventes.countries.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.sventes.countries.model.CountryData;
import lt.sventes.countries.service.CountryService;

@RestController
@Api(value = "country")
@RequestMapping(value = "/api/countries")
public class CountryController {

	private final CountryService countryService;

	// konstruktorius
	@Autowired
	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}

	// Visų šalių gavimas
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get country list", notes = "Returns list of existing countries")
	public List<CountryData> getCountryList() {
		return countryService.getFullListOfCountries();
	}

	// vienos šalies gavimas
	@RequestMapping(path = "/{countryCode}", method = RequestMethod.GET)
	@ApiOperation(value = "Get country", notes = "Returns selected country")
	public CountryData getOneCountryByCountryCode(
			@ApiParam(value = "Country title", required = true) @Valid @PathVariable final String countryCode) {

		return countryService.findCountryByCountryCode(countryCode);
		
	}

	// naujos šalies suvedimas
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new country", notes = "Creates new country with provided data")
	public void createCountry(
			@ApiParam(value = "Country data", required = true) @Valid @RequestBody final CreateCountryCommand ccc) {

		countryService.createCountry(ccc.getTitle(), ccc.getImage(), ccc.getPresident());
	}

	// šalies atnaujinimas
	@RequestMapping(path = "/{countryCode}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Edit country", notes = "Change selected country's data")

	public void updateCountry(
			@ApiParam(value = "Country title", required = true) @Valid @PathVariable final String countryCode,
			@ApiParam(value = "Country data", required = true) @Valid @RequestBody final CreateCountryCommand ccc) {

		countryService.updateCountry(countryCode, ccc.getTitle(), ccc.getImage(), ccc.getPresident());

	}

	// šalies trynimas------------------------------------------

	@RequestMapping(path = "/{countryCode}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete country", notes = "Deletes selected country")

	
	public void deleteCountry(@PathVariable final String countryCode) {
		countryService.deleteCountry(countryCode);
		System.out.println("Deleting country: " + countryCode);
	}
	
	// Vienos šalies švenčių nuskaitymas
		@RequestMapping(path = "/{countryCode}/addedHolidays", method = RequestMethod.GET)
		@ResponseStatus(HttpStatus.OK)
		@ApiOperation(value = "Vienos šalies švenčių gavimas", notes = "Kokioms šventėms yra priskirta šalis")
		public List<String> getCountryHolidays(
				@ApiParam(value = "Holiday title", required = true)
				@PathVariable final String countryCode) {
			return countryService.getCountryHolidays(countryCode);
		}

}
