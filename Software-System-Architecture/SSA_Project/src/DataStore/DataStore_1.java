package DataStore;

/**
 * @author Pramoda Chandru
 * This is class is responsible for storign the data of the vending machine system 1
 */
public class DataStore_1 extends DataStore {

    private float temp_v;

    private float price;

    private float cf;

    private float temp_p;

    public float getTemp_p() {
        return temp_p;
    }

    public void setTemp_p(float p) {
        this.temp_p = p;
    }

    public float getCf() {
        return cf;
    }

    public void setCf(float c) {
        this.cf = c;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float p) {
        this.price = p;
    }

    public float getTemp_v() {
        return temp_v;
    }

    public void setTemp_v(float v) {
        this.temp_v = v;
    }
}
