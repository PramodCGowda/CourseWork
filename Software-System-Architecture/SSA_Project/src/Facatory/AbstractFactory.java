package Facatory;

import DataStore.DataStore;
import Strategy.DispenseAdditive.DispenseAdditive;
import Strategy.DispenseDrink.DispenseDrink;
import Strategy.IncreaseCF.IncreaseCF;
import Strategy.ReturnCoins.ReturnCoins;
import Strategy.StorePrice.StorePrice;
import Strategy.ZeroCF.ZeroCF;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the abstarct factory pattern design
 * This is an abstract class
 */
public abstract class AbstractFactory {
    public abstract DataStore getDataStore();

    public abstract StorePrice getStorePrice();

    public abstract ZeroCF getZeroCF();

    public abstract IncreaseCF getIncreaseCF();

    public abstract ReturnCoins getReturnCoins();

    public abstract DispenseDrink getDispenseDrink();

    public abstract DispenseAdditive getDispenseAdditive();

}
