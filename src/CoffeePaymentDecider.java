import java.util.*;

public class CoffeePaymentDecider {

    public void loadEmployees(ArrayList<Employee> e) {
        Employee alice = new Employee("Alice");
        e.add(alice);
        Employee marcus = new Employee("Marcus");
        e.add(marcus);
        Employee kevin = new Employee("Kevin");
        e.add(kevin);
        Employee joe = new Employee("Joe");
        e.add(joe);
        Employee michael = new Employee("Michael");
        e.add(michael);
    } // End loadEmployees() method
    
    public void initializeMenuAndPrices(ArrayList<String> menu) {
        menu.add("cappuccino");
        menu.add("black coffee");
        menu.add("latte");
        menu.add("chai");
        menu.add("iced tea");
        menu.add("machiato");
        menu.add("rebel energy drink");
    } // End initializeMenuAndPrices
    public static void main(String[] args) {
        CoffeePaymentDecider app = new CoffeePaymentDecider();
        
        ArrayList<Employee> bertramEmployees = new ArrayList<>(); /* store our employee objects */
        ArrayList<String> menu = new ArrayList<>(); /* store our favorite coffee beverages */

        Employee bob = new Employee("Bob");
        Employee jeremy = new Employee("Jeremy");

        /* fill the bertramEmployees list */
        app.loadEmployees(bertramEmployees);
        System.out.println(bertramEmployees.size() == 7);
        
        /* load the menu with items */
        app.initializeMenuAndPrices(menu);
        System.out.println(menu.size() == 7);
        

        /* Associate the coworker with their favorite coffee/beverage */
        HashMap<Employee, String> coworkers = new HashMap<>();
        /* Bob always gets a cappuccino, and Jeremy likes his coffee black */
        coworkers.put(bob, menu.get(0));
        coworkers.put(jeremy, menu.get(1));

        Random rand = new Random();
        for (Employee e : bertramEmployees) {
            coworkers.put(e, menu.get(rand.nextInt(menu.size())));
        } // End for each loop

        /* store the price of the coffee */
        HashMap<String, Double> prices = new HashMap<>();
        prices.put("cappuccino", 4.36);
        prices.put("black coffee", 1.59);
        prices.put("latte", 3.25);
        prices.put("chai", 2.75);
        prices.put("iced tea", 2.25);
        prices.put("rebel energy drink", 3.99);
        prices.put("machiato", 3.50);

        double theBill = 0;
        for (Employee e : bertramEmployees) {
            theBill += prices.get(coworkers.get(e));
        }

        bertramEmployees.add(bob);
        bertramEmployees.add(jeremy);

        Employee prevPaid = null;
        Scanner user_in = new Scanner(System.in);
        while (user_in != null) {
            System.out.println("Who will cover the bill today?\n1 - Roll the dice\n2 - Exit");
            String input = user_in.nextLine();

            if (input.matches("[1-2]")) {
                int userChoice = Integer.parseInt(input);
                if (userChoice == 1) {
                    Employee sacrifice;
                    
                    while (true) { 
                        /* use a random number generator to grab a random coworker */
                        sacrifice = bertramEmployees.get(rand.nextInt(bertramEmployees.size()));

                        if (prevPaid == sacrifice) {
                            System.out.println(sacrifice.getName() + " has previously paid the bill. Lets pick someone else");
                        } else {
                            break;
                        }
                    } // End while loop

                    System.out.println(sacrifice.getName() + " will cover the bill of " + theBill);
                    prevPaid = sacrifice;
                /* output the coworkers name and how much the total bill is */
                } else if (userChoice == 2) {
                    System.out.println("Exiting program");
                    user_in.close();
                    System.exit(0);
                } // End if else if statement 

                user_in.nextLine();
            } else {
                System.out.println("Unrecognized command please try again.");
            } // End if-else statement
        } // End while loop

    } // End main method

} // End CoffeePaymentDecider