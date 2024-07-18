import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;


@WebServlet("/SmartDoorbell")


/* 
	SmartDoorbell class contains class variables name,price,image,retailer,condition,discount and Accessories Hashmap.

	SmartDoorbell class constructor with Arguments name,price,image,retailer,condition,discount and Accessories .
	  
	Accessory class contains getters and setters for name,price,image,retailer,condition,discount and Accessories .

*/

public class SmartDoorbell extends HttpServlet{
	private String id;

	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;
	private String description;

	private int quantity;
	HashMap<String,String> accessories;

	public SmartDoorbell(String name, double price, String image, String retailer, String condition, double discount, String description, int quantity){
		this.id = name;
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.discount = discount;
		this.description = description;
		this.quantity = quantity;
	}

	public SmartDoorbell(String name, double price, String image, String retailer, String condition, double discount, String description, int quantity, String acc){
		this.id = name;
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.discount = discount;
		this.description = description;
		this.accessories=new HashMap<String,String>();
        this.accessories.put(acc,acc);
		this.quantity = quantity;
	}

    HashMap<String,String> getAccessories() {
		return accessories;
		}

	public SmartDoorbell(){
		
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public void setAccessories( HashMap<String,String> accessories) {
		this.accessories = accessories;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
