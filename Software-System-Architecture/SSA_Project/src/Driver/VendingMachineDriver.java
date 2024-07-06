package Driver;

import Facatory.VM1Factory;
import Facatory.VM2Factory;
import VendingMachine.VendingMachine1;
import VendingMachine.VendingMachine2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author Pramoda Chandru
 * This class the main class where execution of the system will begin
 */
public class VendingMachineDriver {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("***************** Welcome to Vending Machine System *****************");
        System.out.println("Select the Vending Machine before proceeding further: ");
        System.out.println("1. Vending-Machine-1");
        System.out.println("2. Vending-Machine-2");
        System.out.println("q. To quit");
        char gptype = '0';
        char input = '1';
        try
        {
            gptype = sc.next().charAt(0);
            switch(gptype) {
                case '1':{
                    // Vending Machine 1 is selected
                    System.out.println("Vending Machine 1 is selected");
                    VM1Factory vm1Factory = new VM1Factory();
                    VendingMachine1 vm1 = new VendingMachine1(vm1Factory);
                    System.out.println(
                            "-------------------------------------------------------------------------------"+
                                    "\nVending Machine-1: " +
                                    "\nMENU of Operations: " +
                                    "\n0. create(int) " +
                                    "\n1. coin(float) " +
                                    "\n2. sugar() " +
                                    "\n3. tea() " +
                                    "\n4. latte() " +
                                    "\n5. insert_cups(int) " +
                                    "\n6. set_price(float) " +
                                    "\n7. cancel() " +
                                    "\nq. quit() " +
                                    "\n-------------------------------------------------------------------------------");
                    while(input!='q')
                    {
                        vm1.printOperations();
                        input= sc.next().charAt(0);
                        switch(input) {
                            case '0':{  // create
                                System.out.println("Operation:  create(int p)");
                                System.out.println("Enter value of the parameter p: ");
                                int p;
                                try {
                                    p = sc.nextInt();
                                    vm1.create(p);
                                }
                                catch(NumberFormatException e)
                                {
                                    System.out.println("Exception");
                                }
                                break;
                            }
                            case '1':{
                                System.out.println("Operation:  coin(float v)");
                                float v;
                                try {
                                    v = sc.nextFloat();
                                    vm1.coin(v);
                                }
                                catch(NumberFormatException e)
                                {
                                    System.out.println("Exception");
                                }
                                break;
                            }
                            case '2': {
                                System.out.println("Operation:  sugar()");
                                vm1.sugar();
                                break;
                            }
                            case '3': {
                                System.out.println("Operation:  tea()");
                                vm1.tea();
                                break;
                            }
                            case '4': {
                                System.out.println("Operation:  latte()");
                                vm1.latte();
                                break;
                            }
                            case '5': {
                                System.out.println("Operation:  insert_cups(int n)");
                                int n;
                                try {
                                    n = sc.nextInt();
                                    vm1.insert_cups(n);
                                }
                                catch(NumberFormatException e)
                                {
                                    System.out.println("Exception");
                                }
                                break;
                            }
                            case '6': {
                                System.out.println("Operation:  set_price(float p)");
                                float p;
                                try {
                                    p = sc.nextFloat();
                                    vm1.set_price(p);
                                }
                                catch(NumberFormatException e)
                                {
                                    System.out.println("Exception");
                                }
                                break;
                            }
                            case '7': {
                                System.out.println("Operation:  cancel()");
                                vm1.cancel();
                                break;
                            }
                            case 'q': {
                                System.out.println("Quit Vending Machine 1");
                                sc.close();
                                System.exit(1);
                            }
                            default: {
                                System.out.println("Invalid selection. Please select operation from provided list");
                            }
                        }
                    }
                }
                case '2':{
                    // Vending Machine 2 is selected
                    System.out.println("Vending Machine 2 is selected");
                    VM2Factory VM2Factory = new VM2Factory();
                    VendingMachine2 vm2 = new VendingMachine2(VM2Factory);
                    System.out.println(
                                "--------------------------------------------------------------------------------"+
                                    "\nVending Machine-2: " +
                                    "\nMENU of Operations: " +
                                    "\n0. CREATE(float) " +
                                    "\n1. COIN(int) " +
                                    "\n2. SUGAR() " +
                                    "\n3. CREAM() " +
                                    "\n4. COFFEE() " +
                                    "\n5. insertCups(int) " +
                                    "\n6. setPrice(int) " +
                                    "\n7. CANCEL() " +
                                    "\n8. CARD(int) " +
                                    "\nq. quit() " +
                                    "\n---------------------------------------------------------------------------");
                    while(input!='q')
                    {
                        vm2.printOperations();
                        input= sc.next().charAt(0);
                        switch(input) {
                            case '0':{
                                System.out.println("Operation:  CREATE(float p)");
                                System.out.println("Enter value of the parameter p: ");
                                float p;
                                try {
                                    p = sc.nextFloat();
                                    vm2.CREATE(p);
                                }
                                catch(NumberFormatException e)
                                {
                                    System.out.println("Exception");
                                }
                                break;
                            }
                            case '1':{
                                System.out.println("Operation:  COIN(int v)");
                                int v;
                                try {
                                    v = sc.nextInt();
                                    vm2.COIN(v);
                                }
                                catch(NumberFormatException e)
                                {
                                    System.out.println("Exception");
                                }
                                break;
                            }
                            case '2': {
                                System.out.println("Operation:  SUGAR()");
                                vm2.SUGAR();
                                break;
                            }
                            case '3': {
                                System.out.println("Operation:  CREAM()");
                                vm2.CREAM();
                                break;
                            }
                            case '4': {
                                System.out.println("Operation:  COFFEE()");
                                vm2.COFFEE();
                                break;
                            }
                            case '5': {
                                System.out.println("Operation:  insertCups(int n)");
                                int n;
                                try {
                                    n = sc.nextInt();
                                    vm2.insertCups(n);
                                }
                                catch(NumberFormatException e)
                                {
                                    System.out.println("Exception");
                                }
                                break;
                            }
                            case '6': {
                                System.out.println("Operation:  setPrice(int p)");
                                int p;
                                try {
                                    p = sc.nextInt();
                                    vm2.setPrice(p);
                                }
                                catch(NumberFormatException e)
                                {
                                    System.out.println("Exception");
                                }
                                break;
                            }
                            case '7': {
                                System.out.println("Operation:  CANCEL()");
                                vm2.CANCEL();
                                break;
                            }
                            case '8': {
                                System.out.println("Operation:  CARD(int x)");
                                int x;
                                try {
                                    x = sc.nextInt();
                                    vm2.CARD(x);
                                }
                                catch(NumberFormatException e)
                                {
                                    System.out.println("Exception");
                                }
                                break;
                            }
                            case 'q': {
                                System.out.println("Quit Vending Machine 2");
                                sc.close();
                                System.exit(1);
                            }
                            default: {
                                System.out.println("Invalid selection. Please select the proper option");
                            }
                        }
                    }
                }
                case 'q':{
                    System.out.println("Thank you for using Vending Machine System");
                    sc.close();
                    break;
                }
                default: {
                    System.out.println("Invalid selection. System is now terminating");
                    System.exit(1);
                    sc.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("An Exception has occurred. System is now terminating");
            System.exit(1);
        }
    }
}