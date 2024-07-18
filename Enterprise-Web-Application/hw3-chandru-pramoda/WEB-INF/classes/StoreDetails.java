import java.util.*;
import java.util.Map;

public class StoreDetails {
    public StoreDetails(int id, String street, String city, String state, int zipcode) {
        super();
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
    public StoreDetails() {}
    private int id;
    private String street;
    private String city;
    private String state;
    private int zipcode;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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