package State;

import MDAEFSM.MDAEFSM;
import OutputProcessor.OutputProcessor;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the state pattern design
 * This is an abstract class responsible for State transition
 */
public abstract class State {
    MDAEFSM m;

    OutputProcessor op;

    public State(MDAEFSM m) {
        this.m = m;
    }

    public void create() {
        invalidState();
    }

    public void coin(int f) {
        invalidState();
    }

    public void insert_cups(int n) {
        invalidState();
    }

    public void set_price() {
        invalidState();
    }

    public void card(int x) {
        invalidState();
    }

    public void dispose_drink(int d) {
        invalidState();
    }

    public void additive(int a) {
        invalidState();
    }

    public void cancel() {
        invalidState();
    }

    /**
     * When an invalid state is selected state class will call this method to inform the user about invalid selection
     */
    private void invalidState() {
        System.out.println("Invalid State "+"\nPlease Select Appropriate operation from the below list");
    }
}
