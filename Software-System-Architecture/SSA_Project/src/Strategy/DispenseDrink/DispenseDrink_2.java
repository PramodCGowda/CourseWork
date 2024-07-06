package Strategy.DispenseDrink;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This is a dispense drink class for vending machine 2
 */
public class DispenseDrink_2 extends DispenseDrink {
    public void dispenseDrink(int id) {
        if(id == 3)
        System.out.println("dispose a cup of coffee");
    }
}
