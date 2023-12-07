package Application.Models;

public class InvoiceDto {

	private int orderId;
	private int phoneNo;
	private String name;
	private String email_id;
	private String products; 
	private double price;
	private int reqQuantity;
	private double amount;
	private double totalAmount;
	private String storeLoc;
	private String product_Id;
	
	
	public String getProduct_Id() {
		return product_Id;
	}
	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getStoreLoc() {
		return storeLoc;
	}
	public void setStoreLoc(String storeLoc) {
		this.storeLoc = storeLoc;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getReqQuantity() {
		return reqQuantity;
	}
	public void setReqQuantity(int reqQuantity) {
		this.reqQuantity = reqQuantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
		
}
