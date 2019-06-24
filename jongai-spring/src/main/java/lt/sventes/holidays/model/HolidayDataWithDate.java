package lt.sventes.holidays.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class HolidayDataWithDate {
	private String code;
	@NotNull
	@Length(min = 1, max = 50)
	private String title;
	@NotNull
	@Length(min = 1, max = 100)
	private String description;
	private String image;
	private String type;
	boolean flag;
	//List<Country> countries;
	private LocalDate simpleDate;
	
	public HolidayDataWithDate(String code, String title, String description, String image, String type, boolean flag,
			LocalDate simpleDate) {
		super();
		this.code = code;
		this.title = title;
		this.description = description;
		this.image = image;
		this.type = type;
		this.flag = flag;
		//this.countries = countries;
		this.simpleDate = simpleDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	

//	public List<Country> getCountries() {
//		return countries;
//	}

//	public void setCountries(List<Country> countries) {
//		this.countries = countries;
//	}
	
	public LocalDate getSimpleDate() {
		return simpleDate;
	}

	public void setSimpleDate(LocalDate simpleDate) {
		this.simpleDate = simpleDate;
	}
	
	

}
