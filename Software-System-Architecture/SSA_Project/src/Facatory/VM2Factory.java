package Facatory;

import DataStore.DataStore;
import DataStore.DataStore_2;
import Strategy.DispenseAdditive.DispenseAdditive;
import Strategy.DispenseAdditive.DispenseAdditive_2;
import Strategy.DispenseDrink.DispenseDrink;
import Strategy.DispenseDrink.DispenseDrink_2;
import Strategy.IncreaseCF.IncreaseCF;
import Strategy.IncreaseCF.IncreaseCF_2;
import Strategy.ReturnCoins.ReturnCoins;
import Strategy.ReturnCoins.ReturnCoins_2;
import Strategy.StorePrice.StorePrice;
import Strategy.StorePrice.StorePrice_2;
import Strategy.ZeroCF.ZeroCF;
import Strategy.ZeroCF.ZeroCF_2;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the abstarct factory pattern design
 * This class creates the object required for the vending machine 2
 */
public class VM2Factory extends AbstractFactory {
    private DataStore dataStore;

    public VM2Factory() {
        this.dataStore = new DataStore_2();
    }

    public DataStore getDataStore() {
        return this.dataStore;
    }

    public StorePrice getStorePrice() {

        return new StorePrice_2(this.dataStore);
    }

    public ZeroCF getZeroCF() {

        return new ZeroCF_2(this.dataStore);
    }

    public IncreaseCF getIncreaseCF() {

        return new IncreaseCF_2(this.dataStore);
    }

    public ReturnCoins getReturnCoins() {

        return new ReturnCoins_2();
    }

    public DispenseDrink getDispenseDrink() {

        return new DispenseDrink_2();
    }

    public DispenseAdditive getDispenseAdditive() {

        return new DispenseAdditive_2();
    }
}
