package Strategy.StorePrice;

import DataStore.DataStore;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This is an abstract class responsible for store price
 */
public abstract class StorePrice {

    DataStore dataStore;
    public StorePrice(DataStore dataStore) {
        this.dataStore = dataStore;

    }

    public abstract void storePrice();
}
