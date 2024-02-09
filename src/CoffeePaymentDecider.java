import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.random.RandomGenerator;

public class CoffeePaymentDecider {

    public void readCommandLine() {
        Scanner user_in = new Scanner(System.in);

        int numEmployees = 0;
        System.out.println("Who all went out for Coffee today?");
        String employeeName = "";

        boolean validInput;
        while (numEmployees < 7) {
            validInput = false;
            employeeName = user_in.nextLine();

            while (validInput == false) {
                if (employeeName.length() < 0 || employeeName.matches("[0-9]")) {
                    System.out.println("Invalid entry please try again");
                } else {
                    validInput = true;
                }
            } // End while loop

            Employee e = new Employee(employeeName);
        } // End while loop

        user_in.close();

    } // End readCommandLine() method
    public static void main(String[] args) {
        
        /* Create Employee objects */
        Employee bob = new Employee("Bob");
        Employee jeremy = new Employee("Jeremy");
        Employee alice = new Employee("Alice");
        Employee marcus = new Employee("Marcus");
        Employee kevin = new Employee("Kevin");
        Employee joe = new Employee("Joe");
        Employee michael = new Employee("Michael");

        HashMap<Employee, String> coworkers = new HashMap<>();
        
        /* Associate the coworker with their favorite coffee/beverage */
        coworkers.put(bob, "cappuccino");
        coworkers.put(jeremy, "black coffee");
        coworkers.put(alice, "latte");
        coworkers.put(marcus, "chai");
        coworkers.put(kevin, "iced tea");
        coworkers.put(joe, "rebel energy drink");
        coworkers.put(michael, "machiato");

        
        
        /* store the coworker with their beverage in a HashMap */
        /* store the price of the coffee */
        /* use a random number generator to grab a random coworker */
        /* output the coworkers name and how much the total bill is */
    
    } // End main method

} // End CoffeePaymentDecider