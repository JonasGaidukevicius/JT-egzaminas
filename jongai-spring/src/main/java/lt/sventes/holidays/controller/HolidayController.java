package lt.sventes.holidays.controller;

import java.util.ArrayList;
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
import lt.sventes.countries.Country;
import lt.sventes.countries.service.CountryService;
import lt.sventes.holidays.CountryOfHoliday;
import lt.sventes.holidays.HolidayData;
import lt.sventes.holidays.service.HolidayService;

@RestController
@Api(value = "holiday")
@RequestMapping(value = "/holidays")
public class HolidayController {

	private final HolidayService holidayService;
	private final CountryService countryService;

	// konstruktorius
	@Autowired
	public HolidayController(HolidayService holidayService, CountryService countryService) {
		this.holidayService = holidayService;
		this.countryService = countryService;
	}

	// Visų švenčių gavimas
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get holiday list", notes = "Returns list of existing holidays")
	public List<HolidayData> getHolidayList() {
		return holidayService.getFullListOfHolidays();
	}

	// vienos šventės gavimas
	@RequestMapping(path = "/{title}", method = RequestMethod.GET)
	@ApiOperation(value = "Get holiday", notes = "Returns selected holiday")
	public HolidayData getHolidayByTitle(
			@ApiParam(value = "Holiday title", required = true) @Valid @PathVariable final String title) {
			return holidayService.findHolidayByTitle(title);
	}

	// naujos šventės suvedimas
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new holiday", notes = "Creates new holiday with provided data")
	public void createHoliday(
			@ApiParam(value = "Holiday data", required = true) @Valid @RequestBody final CreateHolidayCommand chc) {
			holidayService.createHoliday(chc.getTitle(), chc.getDescription(), chc.getImage(), chc.getType(), chc.isFlag(), new ArrayList<Country>());
	}

	// šventės atnaujinimas
	@RequestMapping(path = "/{oldTitle}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Edit holiday", notes = "Change selected holiday's data")
	public void updateHoliday(
			@ApiParam(value = "Holiday title", required = true) @Valid @PathVariable final String oldTitle,
			@ApiParam(value = "Holiday data", required = true) @Valid @RequestBody final CreateHolidayCommand chc) {

		holidayService.updateHoliday(oldTitle, chc.getTitle(), chc.getDescription(), chc.getImage(),
				chc.getType(), chc.isFlag());
	}

	// šventės trynimas------------------------------------------

	@RequestMapping(path = "/{title}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete holiday", notes = "Deletes selected holiday")
	public void deleteHoliday(@PathVariable final String title) {
		holidayService.deleteHoliday(title);
		System.out.println("Deleting holiday: " + title);
	}
	
	// Vienos šventės šalių nuskaitymas
	@RequestMapping(path = "/{title}/addedCountries", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Vienos šventės šalių gavimas", notes = "Kokioms šalims priskirta šventė")
	public List<String> getHolidayCountries(
			@ApiParam(value = "Holiday title", required = true)
			@PathVariable final String title) {
		return holidayService.getHolidayCountries(title);
	}
	
	//Šalių pridėjimas šventei
	@RequestMapping(path = "/{title}/addingCountries", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Šalių šventei pridėjimas", notes = "Pažymėtų šalių pridėjimas šventei")
	public void addCountriesToHoliday(
			@ApiParam(value = "Holiday title", required = true)
			@PathVariable final String title,
			@ApiParam(value = "Countries list", required = true)
			@Valid @RequestBody final List<String> countryList) {
		
		holidayService.addCountryToHoliday(title, countryList);
		return;
	}
	
	//Šalių pašalinimas iš šventės
	@RequestMapping(path = "/{title}/removingCountries", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Šalių pašalinimas iš šventės", notes = "Pažymėtų šalių pašalinimas iš šventės")
	public void removeCountries (
			@ApiParam(value = "Holiday title another", required = true)
			@PathVariable final String title,
			@ApiParam(value = "Countries list another", required = true)
			@Valid @RequestBody final List<String> countryList) {
		holidayService.removeCountryFromHoliday(title, countryList);
		return;
	}
}
