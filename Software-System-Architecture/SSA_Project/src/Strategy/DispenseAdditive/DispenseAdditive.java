package Strategy.DispenseAdditive;

import java.util.List;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This is an abstract class responsible for dispense additive
 */
public abstract class DispenseAdditive {
    public DispenseAdditive() {

    }

    public abstract void dispenseAdditive(List<Integer> a);
}

