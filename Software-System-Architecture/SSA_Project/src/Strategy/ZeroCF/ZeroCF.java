package Strategy.ZeroCF;

import DataStore.DataStore;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This is an abstract class responsible for setting cf value
 */
public abstract class ZeroCF {
    DataStore dataStore;
    public ZeroCF(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public abstract void zeroCF();
}
