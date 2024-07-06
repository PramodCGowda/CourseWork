package State;

import MDAEFSM.MDAEFSM;

import java.util.List;
import java.util.ListIterator;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the state pattern design
 * This is class is responsible for coin inserted state of the vending machine system
 */
public class StateCoinInserted extends State {
    public StateCoinInserted(MDAEFSM m) {
        super(m);
    }

    public void coin(int f) {
        m.getOp().returnCoins();
    }

    public void dispose_drink(int d) {
        if (m.getK() <= 1) {  // if number of cups are less than 1 then change the state to no cups state
            m.changeState(1);
        } else {
            m.setK(m.getK() -1);
            m.getOp().zeroCF();
            m.changeState(2); // change the state to idle state
        }
        m.getOp().dispenseAdditive(m.getA());  //dispose additive
        m.getOp().dispenseDrink(d); //dispose drink
    }

    public void additive(int id) {
        List<Integer> a = m.getA();
        int counter = 1;
        for (ListIterator<Integer> it = a.listIterator(); it.hasNext(); ) {
            Integer element = it.next();
            if (counter == id) {
                /*
                toggle the values of the additive list so that when it is additive will be disposed with the drink
                If it is zero additive will not disposed this can happen when user enter additive twice
                 */
                if (element != 0) {
                    it.set(0);
                } else {
                    it.set(1);
                }
            }
            counter++;
        }
        m.setA(a);
        System.out.println("List of Current Additives: ");
        counter = 1;
        boolean found = false;
        for (Integer element : a) {
            if (counter == 1 && element != 0) {
                System.out.println("SUGAR ");
                found = true;
            } else if (counter == 2 && element != 0) {
                System.out.println("CREAM ");
                found = true;
            }
            counter++;
        }
        if (found) {
            System.out.println();
        } else {
            System.out.println("None");
        }
    }

    /**
     *
     */
    public void cancel() {
        System.out.println("Cancel the transaction");
        m.getOp().returnCoins();
        m.getOp().zeroCF();
        m.changeState(2);
    }
}