package lt.sventes.holidays.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
=======
import javax.persistence.CascadeType;
>>>>>>> a23f698f20cd8278de606c97b3602ad9bad96005
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
<<<<<<< HEAD
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
=======
>>>>>>> a23f698f20cd8278de606c97b3602ad9bad96005
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lt.sventes.cart.model.Cart;
import lt.sventes.countries.model.Country;

@Entity
@Table(name = "holiday", indexes = { @Index(name = "idx_code", columnList = "code", unique = true),
		@Index(name = "idx_title", columnList = "title", unique = true) })
public class Holiday {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column (unique=true, nullable=false)
	private String code;
	@Column(unique=true, nullable=false) 
	private String title;
	@Column
	private String description;
	@Column
	private String image;
	@Column
	private String type;
	@Column
	boolean flag;
	
	//Testinis datos laukelis
	private LocalDate simpleDate; 
	// bandymas su krepšeliu
	@Column
	private int holidayQuantity;
	@Column
	private int holidayCartQuantity;
	
	// JEIGU REIKĖTŲ NAUDOTI *DATE* TIPO LAUKĄ, TAI JAM REIKIA TAIKYTI ANOTACIJĄ
	// *@TEMPORAL
	// @Temporal(TemporalType.DATE)
	// private Date testineData;

	// senasis variantas apjungimo -> čia yra savininkas
	// @ManyToMany
	// @JoinTable(name="holiday_country",
	// joinColumns=@JoinColumn(name = "holiday_id", referencedColumnName = "id"),
	// inverseJoinColumns = @JoinColumn(name = "country_id", referencedColumnName =
	// "id"))
	// private List<Country> countries = new ArrayList<>();
	
	// Dabartinis veikiantis variantas, kai čia ne savininko pusė
	@ManyToMany(mappedBy = "holidays", cascade = CascadeType.ALL)
	private List<Country> countries = new ArrayList<>();
	
	//Čia krepšelis
	@ManyToMany(mappedBy = "holidays")
	List<Cart> carts = new ArrayList<>();
	
	
	//@OneToOne(cascade = {CascadeType.ALL}) // MERGE, CascadeType.DETACH})
	//private ProductDetails productDetails;
	//nebūtinas šitas laukas surišimui
	//@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
	//private List<Cart> cart = new ArrayList<Cart>();

	public Holiday() {
	}

	//konstruktorius be id
	public Holiday(String code, String title, String description, String image, String type, boolean flag,
			List<Country> countries) {
		//this.id = id;
		this.code = code;
		this.title = title;
		this.description = description;
		this.image = image;
		this.type = type;
		this.flag = flag;
		this.countries = countries;
	}
	
	//konstruktorius be id ir su data
		public Holiday(String code, String title, String description, String image, String type, boolean flag,
				List<Country> countries, LocalDate simpleDate) {
			//this.id = id;
			this.code = code;
			this.title = title;
			this.description = description;
			this.image = image;
			this.type = type;
			this.flag = flag;
			this.countries = countries;
			this.simpleDate = simpleDate;
		}
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public List<Country> getCountries() {
		return countries;
	}


	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	public void addCountry(Country country) {
		this.countries.add(country);
		country.getHolidays().add(this);
	}
	
	public void removeCountry(Country country) {
		this.countries.remove(country);
		country.getHolidays().remove(this);
	}
	
	// Testinė data

	public LocalDate getSimpleDate() {
		return simpleDate;
	}

	public void setSimpleDate(LocalDate simpleDate) {
		this.simpleDate = simpleDate;
	}

	// Kiekio seteriai ir geteriai

	public int getHolidayQuantity() {
		return holidayQuantity;
	}

	public void setHolidayQuantity(int holidayQuantity) {
		this.holidayQuantity = holidayQuantity;
	}

	public int getHolidayCartQuantity() {
		return holidayCartQuantity;
	}

	public void setHolidayCartQuantity(int holidayCartQuantity) {
		this.holidayCartQuantity = holidayCartQuantity;
	}
	
}
