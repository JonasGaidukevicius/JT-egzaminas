package lt.sventes.holidays;

import java.util.List;

import lt.sventes.countries.Country;

public class HolidayData {
	private String code;
	private String title;
	private String description;
	private String image;
	private String type;
	boolean flag;
	//List<Country> countries;
	
	public HolidayData(String code, String title, String description, String image, String type, boolean flag) {
		super();
		this.code = code;
		this.title = title;
		this.description = description;
		this.image = image;
		this.type = type;
		this.flag = flag;
		//this.countries = countries;
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
	
	

}
