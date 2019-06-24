package lt.sventes.cart.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lt.sventes.holidays.model.Holiday;
import lt.sventes.users.models.User;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique = true, nullable = false)
	private String cartCode;
	@Column
	private String description;
	@Column
	private CartStatus status;

	// Krepšelio darymas
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user")
	private User user;

	@ManyToMany
	@JoinTable(name = "cart_holiday", joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "holiday_id", referencedColumnName = "id"))
	private List<Holiday> holidays = new ArrayList<>();

	public Cart() {
	}

	public Cart(String cartCode, String description, CartStatus status, User user) {
		super();
		this.cartCode = cartCode;
		this.description = description;
		this.status = status;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCartCode() {
		return cartCode;
	}

	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CartStatus getStatus() {
		return status;
	}

	public void setStatus(CartStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User users) {
		this.user = users;
	}


}
