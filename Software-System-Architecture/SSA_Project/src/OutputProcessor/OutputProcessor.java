package OutputProcessor;

import DataStore.DataStore;
import Facatory.AbstractFactory;
import Strategy.DispenseAdditive.DispenseAdditive;
import Strategy.DispenseDrink.DispenseDrink;
import Strategy.IncreaseCF.IncreaseCF;
import Strategy.ReturnCoins.ReturnCoins;
import Strategy.StorePrice.StorePrice;
import Strategy.ZeroCF.ZeroCF;

import java.util.List;


/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This is class is responsible for calling the oprations(actions) of vending machine system
 */
public class OutputProcessor {
    private DataStore dataStore;
    private StorePrice storePrice;
    private ZeroCF zeroCF;
    private IncreaseCF increaseCF;
    private ReturnCoins returnCoins;
    private DispenseDrink dispenseDrink;
    private DispenseAdditive dispenseAdditive;

    public OutputProcessor(AbstractFactory af) {
        this.dataStore = af.getDataStore();
        this.storePrice = af.getStorePrice();
        this.zeroCF = af.getZeroCF();
        this.increaseCF = af.getIncreaseCF();
        this.returnCoins = af.getReturnCoins();
        this.dispenseDrink = af.getDispenseDrink();
        this.dispenseAdditive = af.getDispenseAdditive();
    }

    public void storePrice() {
        this.storePrice.storePrice();
    }

    public void zeroCF() {
        this.zeroCF.zeroCF();
    }

    public void increaseCF() {
        this.increaseCF.increaseCF();
    }

    public void returnCoins() {
        this.returnCoins.returnCoins();
    }

    public void dispenseDrink(int id) {
        this.dispenseDrink.dispenseDrink(id);
    }

    public void dispenseAdditive(List<Integer> a) {
        this.dispenseAdditive.dispenseAdditive(a);
    }
}