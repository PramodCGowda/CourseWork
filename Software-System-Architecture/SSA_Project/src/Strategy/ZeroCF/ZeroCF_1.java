package Strategy.ZeroCF;

import DataStore.DataStore;
import DataStore.DataStore_1;

import javax.xml.crypto.Data;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This class set the cf to zero for vending machine 1
 */
public class ZeroCF_1 extends ZeroCF {

    public ZeroCF_1(DataStore dataStore) {
        super(dataStore);
    }
    public void zeroCF() {
        DataStore_1 ds1 = new DataStore_1();
        ds1.setCf(0);
        System.out.println("Current balance" +ds1.getCf());
        System.out.println("Reset actual balance");
    }
}
