package DataStore;

/**
 * @author Pramoda Chandru
 * This is class is responsible for storign the data of the vending machine system 2
 */
public class DataStore_2 extends DataStore {
    private float temp_p;
    private int temp_v;

    private float price;

    private int cf;

    public int getTemp_v() {
        return temp_v;
    }

    public void setTemp_v(int temp_v) {
        this.temp_v = temp_v;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCf() {
        return cf;
    }

    public void setCf(int cf) {
        this.cf = cf;
    }

    public float getTemp_p() {
        return temp_p;
    }

    public void setTemp_p(float temp_p) {
        this.temp_p = temp_p;
    }
}
