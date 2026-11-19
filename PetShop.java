import java.util.ArrayList;
import java.util.List;

/**
 * PetShop.java
 *
 * ADDITIONAL CLASS 3 (the manager of the whole system).
 * Holds the pets, the customers and the sales, and offers the operations the shop
 * needs: adding pets, registering customers, selling, and printing reports. It
 * stores every animal as a Pet reference, so one list can hold Dog, Cat, Fish and
 * plain Pet objects together (polymorphism).
 */
public class PetShop {

    private final String shopName;
    private final List<Pet> pets = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Sale> sales = new ArrayList<>();

    public PetShop(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() { return shopName; }

    // ---------------------------------------------------------- setting up the shop
    /** Adds a pet unless the ID already exists. */
    public boolean addPet(Pet pet) {
        if (findPet(pet.getPetId()) != null) {
            System.out.println("  ! Pet ID " + pet.getPetId() + " already exists, not added.");
            return false;
        }
        pets.add(pet);
        return true;
    }

    /** Registers a customer unless the ID already exists. */
    public boolean registerCustomer(Customer customer) {
        if (findCustomer(customer.getCustomerId()) != null) {
            System.out.println("  ! Customer ID " + customer.getCustomerId() + " already exists.");
            return false;
        }
        customers.add(customer);
        return true;
    }

    // -------------------------------------------------------------------- searching
    /** Searches the list with a loop. Returns null when the ID is not found. */
    public Pet findPet(String petId) {
        for (Pet p : pets) {
            if (p.getPetId().equalsIgnoreCase(petId)) {
                return p;
            }
        }
        return null;
    }

    public Customer findCustomer(String customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(customerId)) {
                return c;
            }
        }
        return null;
    }

    // ------------------------------------------------------------------------ selling
    /**
     * Tries to sell a pet. A chain of if statements checks each rule in order and
     * explains the first problem it finds. The sale rule itself is polymorphic:
     * pet.canBeSold() runs the Fish version for fish and the normal version for the rest.
     */
    public Sale sellPet(String customerId, String petId) {
        Customer customer = findCustomer(customerId);
        Pet pet = findPet(petId);

        if (customer == null) {
            System.out.println("  X Sale failed: customer " + customerId + " not found.");
            return null;
        }
        if (pet == null) {
            System.out.println("  X Sale failed: pet " + petId + " not found.");
            return null;
        }
        if (!customer.isActive()) {
            System.out.println("  X Sale failed: account of " + customer.getName() + " is inactive.");
            return null;
        }
        if (!pet.isAvailable()) {
            System.out.println("  X Sale failed: " + pet.getName() + " has already been sold.");
            return null;
        }
        if (!pet.canBeSold()) {
            System.out.println("  X Sale failed: " + pet.getName() + " cannot be sold yet (not vaccinated).");
            return null;
        }

        Sale sale = new Sale(customer, pet);
        sales.add(sale);
        System.out.println("  OK " + sale);
        return sale;
    }

    // ------------------------------------------------------------------------ reports
    /** Prints every pet. displayInfo() shows different details for each type. */
    public void displayInventory() {
        System.out.println("  " + shopName + " inventory (" + pets.size() + " pets)");
        for (Pet p : pets) {
            p.displayInfo();
        }
    }

    /** Prints only the pets that are still in the shop. */
    public void displayAvailablePets() {
        int count = 0;
        for (Pet p : pets) {
            if (p.isAvailable()) {
                System.out.println("  " + p + " - " + p.getPetType() + ", RM " + String.format("%.2f", p.getPrice()));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("  (no pets available)");
        }
    }

    /**
     * The polymorphism showcase: ONE loop and the SAME three method calls on every pet,
     * but each pet answers in its own way.
     */
    public void displayCareReport() {
        for (Pet p : pets) {
            System.out.println(String.format("  %-8s %-8s says %-24s | RM %6.2f per month",
                    p.getName(), p.getPetType(), "\"" + p.makeSound() + "\"", p.calculateMonthlyCareCost()));
            System.out.println("           Tip: " + p.getCareTips());
        }
    }

    /** Prints the customers. */
    public void displayCustomers() {
        for (Customer c : customers) {
            System.out.print("  ");
            c.displayInfo();
        }
    }

    /** Counts pets by type, then adds up the sales using a while loop. */
    public void printSalesReport() {
        int dogs = 0, cats = 0, fish = 0, others = 0, stillInShop = 0;
        for (Pet p : pets) {
            if (p instanceof Dog) {
                dogs++;
            } else if (p instanceof Cat) {
                cats++;
            } else if (p instanceof Fish) {
                fish++;
            } else {
                others++;
            }
            if (p.isAvailable()) {
                stillInShop++;
            }
        }

        double revenue = 0;
        int index = 0;
        while (index < sales.size()) {
            revenue += sales.get(index).getFinalPrice();
            index++;
        }

        System.out.println("  Pets      : " + dogs + " dog(s), " + cats + " cat(s), " + fish
                + " fish, " + others + " other");
        System.out.println("  In shop   : " + stillInShop + " of " + pets.size());
        System.out.println("  Sales made: " + sales.size());
        System.out.println(String.format("  Revenue   : RM %.2f", revenue));
    }
}
