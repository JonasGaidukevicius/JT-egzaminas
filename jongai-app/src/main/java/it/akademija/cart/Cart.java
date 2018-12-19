 package it.akademija.cart;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import it.akademija.products.Product;

@Entity
public class Cart {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Id
	private String username;
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
	private List<Product> products = new ArrayList<Product>();
	
	
	//Map<Product, ProductInfoInCart>
	
	//kita
	//@OneToMany
	//private List<ProductQuantityInCart> products = new ArrayList<ProductQuantityInCart>();
	
	
	
	//@Entity
	//class ProductQuantityInCart {
	//	@OneToOne
	//	Product
	//	quantity
	//}
	
	public Cart() {
	}

	public Cart(String  username) {
		this.username = username;
	}
	
	// toliau - get ir set metodai

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
}
