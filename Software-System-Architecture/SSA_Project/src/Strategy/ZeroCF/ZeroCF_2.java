package Strategy.ZeroCF;

import DataStore.DataStore;
import DataStore.DataStore_2;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This class set the cf to zero for vending machine 2
 */
public class ZeroCF_2 extends ZeroCF {
    public ZeroCF_2(DataStore dataStore) {
        super(dataStore);
    }
    public void zeroCF() {
        DataStore_2 ds2 = new DataStore_2();
        ds2.setCf(0);
        System.out.println("actual balance " +ds2.getCf());
        System.out.println("Reset actual balance");
    }
}
