import java.util.*;

public class CoffeePaymentDecider {

    public static ArrayList<Employee> bertramEmployees = new ArrayList<>(); /* store our employee objects */
    public static ArrayList<String> menu = new ArrayList<>(); /* store our favorite coffee beverages */
    public static HashMap<String, Double> prices = new HashMap<>();
    public static HashMap<Employee, String> coworkers = new HashMap<>();

    public static double theBill = 0;

    public void loadEmployees() {
        // Bob and Jeremy are the two employees specified by name
        Employee bob = new Employee("Bob");
        bertramEmployees.add(bob);
        Employee jeremy = new Employee("Jeremy");
        bertramEmployees.add(jeremy);
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

    public void orderUp() {
        theBill = 0;
    
        coworkers.put(bertramEmployees.get(0), menu.get(0)); /* Bob always get a cappuccino */
        coworkers.put(bertramEmployees.get(1), menu.get(1)); /* Jeremy likes his coffee black */

        Random rand = new Random();
        for (Employee e : bertramEmployees) {
            if (e.equals(bertramEmployees.get(0)) || e.equals(bertramEmployees.get(1))) {
                theBill += prices.get(coworkers.get(e));
            } else {
                coworkers.put(e, menu.get(rand.nextInt(menu.size())));
                theBill += prices.get(coworkers.get(e));
            } // End if-else
        } // End for each loop
    } // End orderUp() method
    public static void main(String[] args) {
        CoffeePaymentDecider app = new CoffeePaymentDecider();
    
        app.loadEmployees(); /* fill the bertramEmployees list */
        app.initializeMenuAndPrices(); /* load the menu with items */

        Employee prevPaid = null;
        String input = "";
        Scanner user_in = new Scanner(System.in);
        Random rand = new Random();

        int numberOfEmployees = 0;
        System.out.println("How many employees are joining Bob and Jeremy for their coffee run?");
        while (true) {
            input = user_in.nextLine();

            if (input.matches("[0-7]")) {
                numberOfEmployees = Integer.parseInt(input);

                /* the regex expression [0-9] takes care of dealing with this input */
                // if (numberOfEmployees <= 0 || numberOfEmployees >= 10) {
                //     System.out.println("Please enter a reasonable amount of employees");
                // } else {
                //     break;
                // }
                break;
            } else {
                System.out.println("Please enter a reasonable amount of employees");
            }
        } // End while loop

        int counter = 0;
        System.out.println("Who is joining Bob and Jeremy for their coffee run?");
        while (counter < numberOfEmployees) {
            input = user_in.nextLine();

            if (!input.matches(".*\\d.*") && input.length() > 0) {
                String name;
                input = input.toLowerCase();
                name = input.substring(0, 1).toUpperCase();
                name += input.substring(1);
                // System.out.println("Employee name: " + name);
                Employee e = new Employee(name);
                bertramEmployees.add(e);
                ++counter;
            } else {
                System.out.println("Please enter a valid name.");
            } // End if-else
        } // End while loop

        System.out.println();

        while (user_in != null) {
            System.out.println("Who will cover the bill today?\n1 - Roll the dice\n2 - Exit");
            input = user_in.nextLine();

            if (input.matches("[1-2]")) {
                int userChoice = Integer.parseInt(input);
                switch (userChoice) {
                    case 1:
                        app.orderUp();
                        Employee randomEmployee;
                        
                        while (true) { 
                            /* use a random number generator to grab a random coworker */
                            randomEmployee = bertramEmployees.get(rand.nextInt(bertramEmployees.size()));

                            if (prevPaid == randomEmployee) {
                                // System.out.println(randomEmployee.getName() + " has previously paid the bill. Lets pick someone else");
                            } else {
                                break;
                            } // End if-else statement
                        } // End while loop

                        System.out.println(String.format(randomEmployee.getName() + " should pay for coffee today. Total cost: $%.2f\n", theBill)); /* output the coworkers name and how much the total bill is */
                        prevPaid = randomEmployee;
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