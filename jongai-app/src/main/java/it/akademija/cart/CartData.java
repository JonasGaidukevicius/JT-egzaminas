package it.akademija.cart;

import java.util.ArrayList;
import java.util.List;

import it.akademija.products.Product;

public class CartData {
	
	private String username;
	private List<Product> products = new ArrayList<Product>();
	
	//konstruktorius
	public CartData() {
		
	}
	
	
	public CartData(String username, List<Product> products) {
		super();
		this.username = username;
		this.products = products;
	}

	//geteriai ir seteriai
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
	
	
}
