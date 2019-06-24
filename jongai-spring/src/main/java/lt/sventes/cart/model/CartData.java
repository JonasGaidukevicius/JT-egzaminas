package lt.sventes.cart.model;

public class CartData {
	private String cartCode;
	private String description;
	private CartStatus status;

	public CartData(String cartCode, String description, CartStatus status) {
		super();
		this.cartCode = cartCode;
		this.description = description;
		this.status = status;
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

}
