package lt.sventes.countries.model;

import javax.persistence.Column;

public class CountryData {
	private String countryCode;
	private String title;
	private String image;
	private String president;
	
	public CountryData(String countryCode, String title, String image, String president) {
		super();
		this.countryCode = countryCode;
		this.title = title;
		this.image = image;
		this.president = president;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPresident() {
		return president;
	}

	public void setPresident(String president) {
		this.president = president;
	}

}