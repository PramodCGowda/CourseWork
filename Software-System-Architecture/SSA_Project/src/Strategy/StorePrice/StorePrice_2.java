package Strategy.StorePrice;

import DataStore.DataStore;
import DataStore.DataStore_2;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This class set the store price of vending machine 2
 */
public class StorePrice_2 extends StorePrice {
    public StorePrice_2(DataStore dataStore) {
        super(dataStore);
    }
    public void storePrice() {
        DataStore_2 ds2 = (DataStore_2) dataStore;
        ds2.setPrice(ds2.getTemp_p());
        float p = ds2.getPrice();
        System.out.println("Price for drink is " +p);
    }
}
