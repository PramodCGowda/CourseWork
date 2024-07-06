package Strategy.DispenseDrink;

/**
 * @author Pramoda Chandru
 * Description: This class is part of the strategy pattern design
 * This is a dispense drink class for vending machine 1
 */
public class DispenseDrink_1 extends DispenseDrink {
    public void dispenseDrink(int id) {
        if(id == 1) {
            System.out.println("dispose a cup of tea");
        } else if (id == 2){
            System.out.println("dispose a cup of latte");
        }
    }
}
