import java.util.Scanner;

/**
 * PetShopMain.java
 *
 * MAIN CLASS.
 * Builds the shop from Assignment 1 (same pets and customers) and then extends it.
 * Part 1 is an automatic demonstration that always prints the same output.
 * Part 2 is the shop menu from Assignment 1 (while loop + switch), which is only
 * started if the user answers "y", so the program never hangs waiting for input.
 */
public class PetShopMain {

    public static void main(String[] args) {

        PetShop shop = new PetShop("Happy Paws Pet Shop");

        // ---------------------------------------------- 1. Create pets (base + derived)
        printHeading("1. Creating pets (base class and derived classes)");
        Pet mochi  = new Cat("P001", "Mochi", 4, 850.00, true, true, true, true);
        Pet buddy  = new Dog("P002", "Buddy", 10, 1500.00, true, true, "Labrador", true);
        Pet cotton = new Pet("P003", "Cotton", "Rabbit", 3, 120.00, false, true);  // plain Pet
        Pet nemo   = new Fish("P004", "Nemo", 8, 25.00, true, false, 20);
        Pet coco   = new Pet();                      // default constructor, then setters
        coco.setPetId("P005");
        coco.setName("Coco");
        coco.setSpecies("Hamster");
        coco.setAgeInMonths(5);
        coco.setPrice(45.00);
        coco.setVaccinated(true);
        Pet luna   = new Cat("P006", "Luna", 2, 1100.00, true, true, true, true);
        Pet rex    = new Dog("P007", "Rex", 5, 900.00, true, true, "Beagle", false);
        Pet coral  = new Fish("P008", "Coral", 12, 180.00, true, true, 100);

        Pet[] newPets = { mochi, buddy, cotton, nemo, coco, luna, rex, coral };
        for (int i = 0; i < newPets.length; i++) {     // for loop adds every pet
            shop.addPet(newPets[i]);
        }
        shop.addPet(new Dog("P001", "Duplicate", 6, 500.0, true, true, "Poodle", true)); // duplicate ID
        shop.displayInventory();

        // ------------------------------------------- 2. Encapsulation and validation
        printHeading("2. Getters, setters and validation (encapsulation)");
        System.out.println("  Cotton's price before: RM " + cotton.getPrice());
        cotton.setPrice(110.00);
        System.out.println("  After setPrice(110.00): RM " + cotton.getPrice());
        try {
            cotton.setPrice(-50);                      // rejected by the setter
        } catch (IllegalArgumentException e) {
            System.out.println("  Rejected invalid price: " + e.getMessage());
        }
        try {
            new Fish("P009", "Bubbles", 3, 30.0, true, false, 0); // invalid tank size
        } catch (IllegalArgumentException e) {
            System.out.println("  Rejected invalid fish: " + e.getMessage());
        }

        // ----------------------------------------- 3. Unique methods of child classes
        printHeading("3. Unique methods of each derived class");
        System.out.println("  Rex (Dog) needs training?       " + ((Dog) rex).needsTraining());
        System.out.println("  Buddy (Dog) needs training?     " + ((Dog) buddy).needsTraining());
        System.out.println("  Mochi (Cat) needs litter tray?  " + ((Cat) mochi).needsLitterTray());
        System.out.println("  Nemo (Fish) beginner friendly?  " + ((Fish) nemo).isBeginnerFriendly());
        System.out.println("  Coral (Fish) beginner friendly? " + ((Fish) coral).isBeginnerFriendly());

        // ------------------------------------------------------- 4. Polymorphism
        printHeading("4. Polymorphism: one loop, same method calls, different answers");
        shop.displayCareReport();

        System.out.println();
        System.out.println("  Can each pet be sold right now? (canBeSold is overridden by Fish)");
        for (Pet p : newPets) {
            String answer = p.canBeSold() ? "yes" : "no";
            System.out.println(String.format("  %-8s %-8s vaccinated=%-5s -> %s",
                    p.getName(), p.getPetType(), p.isVaccinated(), answer));
        }

        // -------------------------------------------------------- 5. Customers
        printHeading("5. Registering customers");
        Customer aiman = new Customer();               // default constructor, then setters
        aiman.setCustomerId("C001");
        aiman.setName("Aiman");
        aiman.setPhone("012-3456789");
        aiman.setTotalSpent(900.00);
        aiman.setLoyaltyPoints(90);
        Customer sofea = new Customer("C002", "Sofea", "019-8765432", "Gold", 320, 3200.00, true);
        Customer hakim = new Customer("C003", "Hakim", "013-5557788", "Standard", 0, 0.0, true);
        Customer lina  = new Customer("C004", "Lina", "017-1112233", "Standard", 0, 0.0, false); // inactive
        shop.registerCustomer(aiman);
        shop.registerCustomer(sofea);
        shop.registerCustomer(hakim);
        shop.registerCustomer(lina);
        shop.displayCustomers();

        // ------------------------------------------------------------ 6. Sales
        printHeading("6. Processing purchase requests (loop + validation)");
        // each row is {customerId, petId}; some are meant to fail
        String[][] requests = {
            { "C001", "P001" },   // Aiman (Standard) buys Mochi, no discount, upgrades to Silver
            { "C001", "P003" },   // fails: Cotton is not vaccinated
            { "C001", "P002" },   // Aiman (now Silver) buys Buddy with 8% off, upgrades to Gold
            { "C002", "P001" },   // fails: Mochi already sold
            { "C002", "P004" },   // Sofea (Gold) buys Nemo: fish need no vaccination, 15% off
            { "C009", "P005" },   // fails: unknown customer
            { "C003", "P999" },   // fails: unknown pet
            { "C004", "P005" },   // fails: inactive account
            { "C003", "P007" },   // Hakim buys Rex (RM 900), still Standard
            { "C003", "P008" }    // Hakim buys Coral, total passes RM 1000 so he becomes Silver
        };
        int i = 0;
        while (i < requests.length) {                  // while loop goes through every request
            System.out.println("  Request " + (i + 1) + ": " + requests[i][0] + " wants " + requests[i][1]);
            shop.sellPet(requests[i][0], requests[i][1]);
            i++;
        }

        // -------------------------------------------------------- 7. After the sales
        printHeading("7. Customers after the sales");
        shop.displayCustomers();

        printHeading("8. Pets still available");
        shop.displayAvailablePets();

        printHeading("9. Shop report");
        shop.printSalesReport();

        // --------------------------------------------- 10. Interactive menu (optional)
        printHeading("10. Shop menu (from Assignment 1)");
        Scanner input = new Scanner(System.in);
        System.out.print("  Open the shop menu for Aiman? (y/n): ");
        String answer = input.hasNextLine() ? input.nextLine().trim() : "n";
        System.out.println();
        if (answer.equalsIgnoreCase("y")) {
            runMenu(shop, aiman, input);
        } else {
            System.out.println("  Menu skipped. Thanks for visiting " + shop.getShopName() + "!");
        }
    }

    /** The menu from Assignment 1: a while loop keeps running until the user picks Exit. */
    private static void runMenu(PetShop shop, Customer customer, Scanner input) {
        boolean running = true;
        while (running) {
            System.out.println("\n  1. View available pets");
            System.out.println("  2. Buy a pet");
            System.out.println("  3. View my account");
            System.out.println("  4. Exit");
            System.out.print("  Choose: ");

            int choice = input.hasNextInt() ? input.nextInt() : 4;
            if (input.hasNextLine()) {
                input.nextLine(); // clear the leftover newline
            }

            switch (choice) {
                case 1:
                    shop.displayAvailablePets();
                    break;
                case 2:
                    System.out.print("  Enter pet ID (e.g. P005): ");
                    String id = input.hasNextLine() ? input.nextLine().trim() : "";
                    shop.sellPet(customer.getCustomerId(), id);
                    break;
                case 3:
                    System.out.print("  ");
                    customer.displayInfo();
                    break;
                case 4:
                    running = false;
                    System.out.println("  Thanks for visiting!");
                    break;
                default:
                    System.out.println("  Please pick 1 to 4.");
            }
        }
    }

    /** Small helper that prints a section title. */
    private static void printHeading(String text) {
        System.out.println();
        System.out.println("=== " + text + " ===");
    }
}
