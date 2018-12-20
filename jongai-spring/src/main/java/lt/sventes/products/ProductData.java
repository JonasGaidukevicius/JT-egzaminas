package lt.sventes.products;



public class ProductData {
	private long id;
	private String title;
	private double price;
	private int quantity;
	private ProductDetails productDetails;
	
	public ProductData() {
		
	}
	
	public ProductData(long id, String title, double price, int quantity, ProductDetails productDetails) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
		this.productDetails = productDetails;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
	
	
	
	
}




