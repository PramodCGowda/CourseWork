package MDAEFSM;

import OutputProcessor.OutputProcessor;
import State.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pramoda Chandru
 * Description: This class is a context class
 * This is class is responsible for actual events of the vending machine system
 */
public class MDAEFSM {
    public State s; //holds the current state reference
    public State[] LS; //holds the state list
    private OutputProcessor op;
    private int k;  //holds the number of cups available

    private List<Integer> A = new ArrayList<>(); //additives

    public MDAEFSM() {
        // list of states in the EFSM
        LS = new State[4];

        // instantiate each state, passing in a reference to this VM class
        LS[0] = new StateStart(this);
        LS[1] = new StateNoCups(this);
        LS[2] = new StateIdle(this);
        LS[3] = new StateCoinInserted(this);

        s = LS[0]; // Initial state is set to start

        for(int i = 0; i < 2; i++) {
            A.add(0);   //initialize the additives to 0
        }
    }

    public OutputProcessor getOp() {
        return op;
    }

    public void setOp(OutputProcessor op) {
        this.op = op;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void create() {
        s.create();
    }

    public void coin(int f) {
        s.coin(f);
    }

    public void insert_cups(int n) {
        s.insert_cups(n);
    }

    public void set_price() {
        s.set_price();
    }

    public void card(int x) {
        s.card(x);
    }

    public void dispose_drink(int d) {
        s.dispose_drink(d);
    }

    public void additive(int a) {
        s.additive(a);
    }

    public void cancel() {
        s.cancel();
    }

    public void changeState(int id) {
        s = LS[id];
    }

    public List<Integer> getA()
    {
        return A;
    }

    public void setA(List<Integer> a)
    {
        A = a;
    }
}
