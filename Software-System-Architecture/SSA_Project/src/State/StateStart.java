package State;

import MDAEFSM.MDAEFSM;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the state pattern design
 * This is class is responsible for start state of the vending machine system
 */
public class StateStart extends State{
    public StateStart(MDAEFSM m) {
        super(m);
    }

    public void create() {
            m.getOp().storePrice();
            m.changeState(1);  //change state from start to insert cups
    }
}
