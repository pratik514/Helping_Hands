package Application.Models;


public class ProductsDto {
	 
	private String products; 
	private boolean inStock;
	private int offer;
	private double price;
	private int quantity;
	private int reqQuantity;
	private int productId;

	
	public int getReqQuantity() {
		return reqQuantity;
	}
	public void setReqQuantity(int reqQuantity) {
		this.reqQuantity = reqQuantity;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}
	public boolean isInStock() {
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	public int getOffer() {
		return offer;
	}
	public void setOffer(int offer) {
		this.offer = offer;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
		
}
