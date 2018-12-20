package lt.sventes.cart;

public final class CreateCartCommand {
	private long id;
	private String title;
	private int quantity;
	
		
	// toliau - get ir set metodai
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
