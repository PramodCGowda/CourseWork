import java.util.*;
import java.util.Map;

public class CustomerDetails {
    public CustomerDetails(String customer_name, String street, String city, String state, int zipcode) {
        super();
        this.customer_name = customer_name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
    public CustomerDetails() {}
    private String customer_name;
    private String street;
    private String city;
    private String state;
    private int zipcode;
    public String getCustomerName() {
        return customer_name;
    }
    public void setCustomerName(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getstreet() {
        return street;
    }
    public void setstreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public int getZipcode() {
        return zipcode;
    }
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
