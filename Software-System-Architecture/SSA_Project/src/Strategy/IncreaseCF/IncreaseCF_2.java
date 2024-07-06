package Strategy.IncreaseCF;

import DataStore.DataStore;
import DataStore.DataStore_2;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This class increase the value of cf for vending machine 2
 */
public class IncreaseCF_2 extends IncreaseCF {
    public IncreaseCF_2(DataStore dataStore) {
        super(dataStore);
    }

    public void increaseCF() {
        DataStore_2 ds2 = (DataStore_2) dataStore;
        ds2.setCf(ds2.getCf() + ds2.getTemp_v());
        float b = ds2.getCf();
        System.out.println("Current balance is "+b);
        float p = ds2.getPrice();
        System.out.println("Price for a drink is "+p);
    }
}
