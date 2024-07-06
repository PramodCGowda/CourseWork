package VendingMachine;

import DataStore.DataStore_2;
import Facatory.AbstractFactory;

public class VendingMachine2 extends VendingMachine {
    public VendingMachine2(AbstractFactory af) {
        super(af);
    }

    @Override
    public void printOperations() {
        System.out.println(
                "-------------------------------------------------------------------------------"+
                        "\nSelect operation: " +
                        "0. CREATE, " +
                        "1. COIN, " +
                        "2. SUGAR, " +
                        "3. CREAM, " +
                        "4. COFFEE, " +
                        "5. insertCups, " +
                        "6. setPrice, " +
                        "7. CANCEL, " +
                        "8. CARD, " +
                        "q. quit");
    }

    public void CREATE(float p) {
        DataStore_2 ds2 = (DataStore_2) this.dataStore;
        ds2.setTemp_p(p);
        m.create();
    }

    public void COIN(int v) {
        DataStore_2 ds2 = (DataStore_2) this.dataStore;
        ds2.setTemp_v(v);
        if(ds2.getCf() + v >= ds2.getPrice()) {
            m.coin(1);
        } else {
            m.coin(0);
        }
    }

    public void SUGAR() {
        m.additive(1);
    }

    public void CREAM() {
        m.additive(2);
    }

    public void COFFEE() {
        m.dispose_drink(3);
    }

    public void insertCups(int n) {
        m.insert_cups(n);
    }

    public void setPrice(int p) {
        DataStore_2 ds2 = (DataStore_2) this.dataStore;
        ds2.setTemp_p(p);
        m.set_price();
    }

    public void CANCEL() {
        m.cancel();
    }

    public void CARD(int x) {
        DataStore_2 ds2 = (DataStore_2) this.dataStore;
        if(x >= ds2.getPrice()) {
            m.card(x);
        }
    }
}