package Strategy.IncreaseCF;

import DataStore.DataStore;
import DataStore.DataStore_1;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This class increase the value of cf for vending machine 1
 */
public class IncreaseCF_1 extends IncreaseCF {
    public IncreaseCF_1(DataStore dataStore) {
        super(dataStore);
    }

    public void increaseCF() {
        DataStore_1 ds1 = (DataStore_1) dataStore;
        ds1.setCf(ds1.getCf() + ds1.getTemp_v());
        float b = ds1.getCf();
        System.out.println("Current balance is "+b);
        float p = ds1.getPrice();
        System.out.println("Price for a drink is "+p);
    }
}
