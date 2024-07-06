package VendingMachine;

import DataStore.DataStore;
import Facatory.AbstractFactory;
import MDAEFSM.MDAEFSM;
import OutputProcessor.OutputProcessor;

public abstract class VendingMachine {
    MDAEFSM m;
    DataStore dataStore;

    VendingMachine(AbstractFactory af) {
        this.dataStore = af.getDataStore();
        this.m = new MDAEFSM();
        this.m.setOp(new OutputProcessor(af));
    }

    public abstract void printOperations();
}
