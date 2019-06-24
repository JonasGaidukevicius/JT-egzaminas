package lt.sventes.holidays.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lt.sventes.countries.model.Country;
import lt.sventes.countries.service.CountryService;
import lt.sventes.holidays.model.HolidayData;
import lt.sventes.holidays.model.HolidayDataWithDate;
import lt.sventes.holidays.service.HolidayService;

@RestController
@Api(value = "holiday")
@RequestMapping(value = "/api/holidays")
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
	/*@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get holiday list", notes = "Returns list of existing holidays")
	public List<HolidayData> getHolidayList() {
		return holidayService.getFullListOfHolidays();
	}*/
	
	// Visų švenčių gavimas su datos lauku
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get holiday list", notes = "Returns list of existing holidays")
	public List<HolidayDataWithDate> getHolidayListWithDate() {
		return holidayService.getFullListOfHolidaysWithDate();
	}

	// vienos šventės gavimas
	@RequestMapping(path = "/{code}", method = RequestMethod.GET)
	@ApiOperation(value = "Get holiday", notes = "Returns selected holiday")
	public HolidayDataWithDate getHolidayByTitle(
			@ApiParam(value = "Holiday title", required = true) @PathVariable final String code) {
			return holidayService.findOneHolidayByCode(code);
			//return holidayService.findHolidayByTitle(title);
	}

	// naujos šventės suvedimas
	/*@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create new holiday", notes = "Creates new holiday with provided data")
	public ResponseEntity<?> createHoliday(
			@ApiParam(value = "Holiday data", required = true) @Valid @RequestBody final CreateHolidayCommand chc) {
		return holidayService.createHoliday(chc.getTitle(), chc.getDescription(), chc.getImage(), chc.getType(),
				chc.isFlag(), new ArrayList<Country>());
	}*/
	
	// naujos šventės SU DATA suvedimas
		@RequestMapping(method = RequestMethod.POST)
		@ResponseStatus(HttpStatus.CREATED)
		@ApiOperation(value = "Create new holiday", notes = "Creates new holiday with provided data")
		public ResponseEntity<?> createHolidayWithDate(
				@ApiParam(value = "Holiday data", required = true) @Valid @RequestBody final CreateHolidayCommand chc) {
			return holidayService.createHolidayWithDate(chc.getTitle(), chc.getDescription(), chc.getImage(), chc.getType(),
					chc.isFlag(), new ArrayList<Country>(), chc.getSimpleDate());
		}

	// šventės atnaujinimas
	@RequestMapping(path = "/{code}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Edit holiday", notes = "Change selected holiday's data")
	public void updateHoliday(
			@ApiParam(value = "Holiday code", required = true) @PathVariable final String code,
			@ApiParam(value = "Holiday data", required = true) @Valid @RequestBody final CreateHolidayCommand chc) {

		holidayService.updateHoliday(code, chc.getTitle(), chc.getDescription(), chc.getImage(),
				chc.getType(), chc.isFlag());
	}

	// šventės trynimas------------------------------------------

	@RequestMapping(path = "/{code}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete holiday", notes = "Deletes selected holiday")
	public void deleteHoliday(@PathVariable final String code) {
		holidayService.deleteHoliday(code);
		System.out.println("Deleting holiday: " + code);
	}
	
	// Vienos šventės šalių nuskaitymas
	@RequestMapping(path = "/{code}/addedCountries", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Vienos šventės šalių gavimas", notes = "Kokioms šalims priskirta šventė")
	public List<String> getHolidayCountries(
			@ApiParam(value = "Holiday title", required = true)
			@PathVariable final String code) {
		return holidayService.getHolidayCountries(code);
	}
	
	//Šalių pridėjimas šventei
	@RequestMapping(path = "/{code}/addingCountries", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Šalių šventei pridėjimas", notes = "Pažymėtų šalių pridėjimas šventei")
	public void addCountriesToHoliday(
			@ApiParam(value = "Holiday title", required = true)
			@PathVariable final String code,
			@ApiParam(value = "Countries list", required = true)
			@Valid @RequestBody final List<String> countryList) {
		
		holidayService.addCountryToHoliday(code, countryList);
		return;
	}
	
	//Šalių pašalinimas iš šventės
	@RequestMapping(path = "/{code}/removingCountries", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Šalių pašalinimas iš šventės", notes = "Pažymėtų šalių pašalinimas iš šventės")
	public void removeCountries (
			@ApiParam(value = "Holiday title another", required = true)
			@PathVariable final String code,
			@ApiParam(value = "Countries list another", required = true)
			@Valid @RequestBody final List<String> countryList) {
		holidayService.removeCountryFromHoliday(code, countryList);
		return;
	}
}
