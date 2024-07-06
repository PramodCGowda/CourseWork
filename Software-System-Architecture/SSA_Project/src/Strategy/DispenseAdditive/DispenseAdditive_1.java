package Strategy.DispenseAdditive;

import java.util.List;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This is a dispense additive class for vending machine 1
 */
public class DispenseAdditive_1 extends DispenseAdditive {
    public void dispenseAdditive(List<Integer> a) {
        int counter = 1;
        for (int i : a) {
            if (counter == 1 && i == 1) {
                System.out.println("add some sugar");
            }
            counter++;
        }
    }
}