package Facatory;

import DataStore.DataStore;
import DataStore.DataStore_1;
import Strategy.DispenseAdditive.DispenseAdditive;
import Strategy.DispenseAdditive.DispenseAdditive_1;
import Strategy.DispenseDrink.DispenseDrink;
import Strategy.DispenseDrink.DispenseDrink_1;
import Strategy.IncreaseCF.IncreaseCF;
import Strategy.IncreaseCF.IncreaseCF_1;
import Strategy.ReturnCoins.ReturnCoins;
import Strategy.ReturnCoins.ReturnCoins_1;
import Strategy.StorePrice.StorePrice;
import Strategy.StorePrice.StorePrice_1;
import Strategy.ZeroCF.ZeroCF;
import Strategy.ZeroCF.ZeroCF_1;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the abstarct factory pattern design
 * This class creates the object required for the vending machine 1
 */
public class VM1Factory extends AbstractFactory {

    private DataStore dataStore;

    public VM1Factory() {
            this.dataStore = new DataStore_1();
    }

    public DataStore getDataStore() {
        return this.dataStore;
    }

    public StorePrice getStorePrice() {
        return new StorePrice_1(this.dataStore);
    }

    public ZeroCF getZeroCF() {
        return new ZeroCF_1(this.dataStore);
    }

    public IncreaseCF getIncreaseCF() {

        return new IncreaseCF_1(this.dataStore);
    }

    public ReturnCoins getReturnCoins() {

        return new ReturnCoins_1();
    }

    public DispenseDrink getDispenseDrink() {

        return new DispenseDrink_1();
    }

    public DispenseAdditive getDispenseAdditive() {

        return new DispenseAdditive_1();
    }
}