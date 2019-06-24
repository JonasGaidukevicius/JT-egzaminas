package lt.sventes.countries.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lt.sventes.holidays.model.Holiday;

@Entity
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column (unique=true, nullable=false)
	private String countryCode;
	@Column(unique=true, nullable=false) 
	private String title;
	@Column
	private String image;
	@Column
	private String president;

	// senasis apjungimo -> čia yra ne savininkas
	// @ManyToMany(mappedBy="countries")
	// List<Holiday> holidays = new ArrayList<>();
	
	// Dabartinis veikiantis variantas, kai čia yra savininko pusė
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "holiday_country", joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "holiday_id", referencedColumnName = "id"))
	List<Holiday> holidays = new ArrayList<>();

	//@OneToOne(cascade = {CascadeType.ALL}) // MERGE, CascadeType.DETACH})
	//private ProductDetails productDetails;
	//nebūtinas šitas laukas surišimui
	//@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
	//private List<Cart> cart = new ArrayList<Cart>();

	public Country() {
	}

	//konstruktorius be id
	public Country(String countryCode, String title, String image, String president) {
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Holiday> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}
	
	public void addHoliday(Holiday holiday) {
		this.holidays.add(holiday);
		holiday.getCountries().add(this);
	}
	
	public void removeHoliday(Holiday holiday) {
		this.holidays.remove(holiday);
		holiday.getCountries().remove(this);
	}
	
}



	