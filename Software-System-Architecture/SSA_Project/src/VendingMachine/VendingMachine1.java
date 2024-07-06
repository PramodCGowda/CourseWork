package VendingMachine;

import DataStore.DataStore_1;
import Facatory.AbstractFactory;

public class VendingMachine1 extends VendingMachine {
    public VendingMachine1(AbstractFactory af) {
        super(af);
    }

    @Override
    public void printOperations() {
        System.out.println(
                        "-------------------------------------------------------------------------------"+
                        "\nSelect operation: " +
                        "0. create, " +
                        "1. coin, " +
                        "2. sugar, " +
                        "3. tea, " +
                        "4. latte, " +
                        "5. insert_cups, " +
                        "6. set_price, " +
                        "7. cancel, " +
                        "q. quit");
    }

    public void create(int p) {
        DataStore_1 ds1 = (DataStore_1) this.dataStore;
        ds1.setTemp_p(p);
        m.create();
    }

    public void coin(float v) {
        DataStore_1 ds1 = (DataStore_1) this.dataStore;
        ds1.setTemp_v(v); //TODO temp and cf values are not reset - investigate
        if(ds1.getCf() + v >= ds1.getPrice()) {
            m.coin(1);
        } else {
            m.coin(0);
        }
    }

    public void sugar() {
        m.additive(1);
    }

    public void tea() {
        m.dispose_drink(1);
    }

    public void latte() {
        m.dispose_drink(2);
    }

    public void insert_cups(int n) {
        m.insert_cups(n);
    }

    public void set_price(float p) {
        DataStore_1 ds1 = (DataStore_1) this.dataStore;
        ds1.setTemp_p(p);
        m.set_price();
    }

    public void cancel() {
        m.cancel();
    }
}
