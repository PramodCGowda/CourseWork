package Strategy.StorePrice;


import DataStore.DataStore;
import DataStore.DataStore_1;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This class set the store price of vending machine 1
 */
public class StorePrice_1 extends StorePrice {

    public StorePrice_1(DataStore dataStore) {
        super(dataStore);
    }
    public void storePrice() {
        DataStore_1 ds1 = (DataStore_1) dataStore;
        ds1.setPrice(ds1.getTemp_p());
        float p = ds1.getPrice();
        System.out.println("Price for drink is " +p);
    }
}
