package State;

import MDAEFSM.MDAEFSM;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the state pattern design
 * This is class is responsible for idle state of the vending machine system
 */
public class StateIdle extends State {
    public StateIdle(MDAEFSM m) {
        super(m);
    }

    /**
     * Accepts coin and perform the change state
     * @param f - number of coins
     */
    public void coin(int f) {
        if(f == 1)  { //if sufficient coins are inserted then perform the below actions
            List<Integer> l = new ArrayList<>();
            for(int i = 0; i < 2; i++) {
                l.add(i, 0);
            }
            m.setA(l);
            m.changeState(3); //change state from idle to coinInserted
        }
        m.getOp().increaseCF();
    }

    /**
     * Increment the number of cups count
     * @param n - number of cups
     */
    public void insert_cups(int n) {
        if(n > 0) {
            m.setK(m.getK() + n);
        }
        System.out.println("Number of cups: " + m.getK());
    }

    /**
     * set price
     */
    public void set_price() {
        m.getOp().storePrice();
    }

    /**
     * Accepts payment in card and perform change of state
     * @param x - amount
     */
    public void card(int x) {
        List<Integer> l = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            l.add(i, 0);
        }
        m.setA(l);
        m.getOp().zeroCF();
        m.changeState(3); //change state from idle to coinInserted
    }
}
