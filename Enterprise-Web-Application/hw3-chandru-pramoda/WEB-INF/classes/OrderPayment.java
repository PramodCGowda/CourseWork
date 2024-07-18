import java.io.*;
import java.time.LocalDate;


/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable{
	private int orderId;
	private String user_id;
	private String customer_name;
	private String customer_address;
	private String creditCardNo;
	private String orderName;
	private double orderPrice;
	private LocalDate purchaseDate;
	private LocalDate shipDate;
	private String productId;
	private String productType;
	private int quantity;
	private double productPrice;
	private double shippingCost;
	private double productDiscount;
	private double total_sales;
	private String storeID;

	public OrderPayment(int orderId, String user_id, String customer_name, String customer_address, String creditCardNo, String orderName,
						double orderPrice, LocalDate purchaseDate, LocalDate shipDate, String productId, String productType, int quantity,
						double productPrice, double shippingCost, double productDiscount, double total_sales, String storeID) {
		this.orderId = orderId;
		this.user_id = user_id;
		this.customer_name = customer_name;
		this.customer_address = customer_address;
		this.creditCardNo = creditCardNo;
		this.orderName = orderName;
		this.orderPrice = orderPrice;
		this.purchaseDate = purchaseDate;
		this.shipDate = shipDate;
		this.productId = productId;
		this.productType = productType;
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.shippingCost = shippingCost;
		this.productDiscount = productDiscount;
		this.total_sales = total_sales;
		this.storeID = storeID;
	}

	public OrderPayment(int orderId, String customer_name, String orderName, double orderPrice, String customer_address, String creditCardNo) {
		this.orderId = orderId;
		this.customer_name = customer_name;
		this.customer_address = customer_address;
		this.creditCardNo = creditCardNo;
		this.orderName = orderName;
		this.orderPrice = orderPrice;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCustomername() {
		return customer_name;
	}

	public void setCustomername(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public LocalDate getShipDate() {
		return shipDate;
	}

	public void setShipDate(LocalDate shipDate) {
		this.shipDate = shipDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public double getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}

	public double getTotal_sales() {
		return total_sales;
	}

	public void setTotal_sales(double total_sales) {
		this.total_sales = total_sales;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

}
