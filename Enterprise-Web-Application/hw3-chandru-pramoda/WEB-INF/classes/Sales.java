
public class Sales {
	String id;
	String name;
	double price;
	double totalsale;
	int quantity;
	String type;

	public Sales(String id, String name, double price, int quantity, String type) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
	}

	public Sales(String id, String name, double price, double totalsale, int quantity, String type) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.totalsale =totalsale;
		this.quantity = quantity;
		this.type = type;
	}
	public double getTotalSale(
		) {
		return price * (double) quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
