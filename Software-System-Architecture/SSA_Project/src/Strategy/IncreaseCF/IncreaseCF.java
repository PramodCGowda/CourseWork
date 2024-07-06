package Strategy.IncreaseCF;

import DataStore.DataStore;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This is an abstract class responsible to increase cf
 */
public abstract class IncreaseCF {

    DataStore dataStore;
    public IncreaseCF(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public abstract void increaseCF();
}
