import java.util.*;

public class CoffeePaymentDecider {

    public static ArrayList<Employee> bertramEmployees = new ArrayList<>(); /* store our employee objects */
    public static ArrayList<String> menu = new ArrayList<>(); /* store our favorite coffee beverages */
    public static HashMap<String, Double> prices = new HashMap<>();
    public static HashMap<Employee, String> coworkers = new HashMap<>();

    public static double theBill = 0;

    public void loadEmployees() {
        Employee bob = new Employee("Bob");
        bertramEmployees.add(bob);

        Employee jeremy = new Employee("Jeremy");
        bertramEmployees.add(jeremy);

        Employee alice = new Employee("Alice");
        bertramEmployees.add(alice);
        
        Employee marcus = new Employee("Marcus");
        bertramEmployees.add(marcus);

        Employee kevin = new Employee("Kevin");
        bertramEmployees.add(kevin);
        
        Employee joe = new Employee("Joe");
        bertramEmployees.add(joe);

        Employee michael = new Employee("Michael");
        bertramEmployees.add(michael);
    } // End loadEmployees() method
    
    public void initializeMenuAndPrices() {
        menu.add("cappuccino");
        menu.add("black coffee");
        menu.add("latte");
        menu.add("chai");
        menu.add("iced tea");
        menu.add("machiato");
        menu.add("rebel energy drink");

        prices.put("cappuccino", 4.36);
        prices.put("black coffee", 1.59);
        prices.put("latte", 3.25);
        prices.put("chai", 2.75);
        prices.put("iced tea", 2.25);
        prices.put("rebel energy drink", 3.99);
        prices.put("machiato", 3.50);
    } // End initializeMenuAndPrices

    public void calculateBill() {
        theBill = 0;
    
        coworkers.put(bertramEmployees.get(bertramEmployees.indexOf(bob)), menu.get(0)); /* Bob always get a cappuccino */
        coworkers.put(jeremy, menu.get(1)); /* Jeremy likes his coffee black */

        Random rand = new Random();
        for (Employee e : bertramEmployees) {
            if (e.equals(bertramEmployees.indexOf(bob))) {
                coworkers.put(bob, menu.get(0));
            } else if (e.equals(jeremy)) {
                coworkers.put(jeremy, menu.get(1));
            } else {
                coworkers.put(e, menu.get(rand.nextInt(menu.size())));
            } // End if-else statements
        
            theBill += prices.get(coworkers.get(e));
        } // End for each loop
    } // End orderUp() method
    public static void main(String[] args) {
        CoffeePaymentDecider app = new CoffeePaymentDecider();
    
        app.loadEmployees(); /* fill the bertramEmployees list */
        app.initializeMenuAndPrices(); /* load the menu with items */

        Employee prevPaid = null;
        Scanner user_in = new Scanner(System.in);
        Random rand = new Random();

        while (user_in != null) {
            System.out.println("Who will cover the bill today?\n1 - Roll the dice\n2 - Exit");
            String input = user_in.nextLine();

            if (input.matches("[1-2]")) {
                int userChoice = Integer.parseInt(input);
                switch (userChoice) {
                    case 1:
                        app.orderUp();
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

                        System.out.println(String.format(sacrifice.getName() + " will cover the bill of %.2f\n", theBill)); /* output the coworkers name and how much the total bill is */
                        prevPaid = sacrifice;
                        break;
                    case 2:
                        System.out.println("Exiting program");
                        user_in.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Unrecognized command. Please try again\n");
                        break;
                } // End switch statement
            } else {
                System.out.println("Unrecognizd command. Please try again\n");
            } // End if-else statement

        } // End while loop

    } // End main method

} // End CoffeePaymentDecider