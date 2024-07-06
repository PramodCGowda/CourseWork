package State;

import MDAEFSM.MDAEFSM;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the state pattern design
 * This is class is responsible for No cups state of the vending machine system
 */
public class StateNoCups extends State {

    /**
     * constructor
     * @param m
     */
    public StateNoCups(MDAEFSM m) {
        super(m);
    }

    /**
     * returns the coin as no cups are available
     * @param f - number of coins
     */
    public void coin(int f) {
        m.getOp().returnCoins();
    }

    /**
     * Increment the number of cups count
     * @param n - number of cups
     */
    public void insert_cups(int n) {
        if (n > 0) {
            m.setK(n);  //set the number cup count
            m.getOp().zeroCF(); // set the CF to 0
            m.changeState(2); //change state from insert cups to idle state
        }
        System.out.println("Number of cups: " + m.getK());
    }
}