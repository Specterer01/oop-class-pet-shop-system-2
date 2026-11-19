/**
 * Pet.java
 *
 * BASE CLASS (superclass) of the system.
 * This is the Pet class from Assignment 1, now extended so it can be the parent of
 * Dog, Cat and Fish. It keeps the same seven attributes, the default and
 * parameterized constructors, the getters and setters and the extra methods.
 * New in Assignment 2: it implements the Careable interface and has methods that
 * the child classes override (makeSound, calculateMonthlyCareCost, getCareTips,
 * canBeSold, getPetType and getDetails).
 *
 * ENCAPSULATION: every attribute is private. Other classes only reach them through
 * getters and setters, and the setters reject invalid values.
 */
public class Pet implements Careable {

    // ---------- Attributes (all private) ----------
    private String petId;        // unique code, e.g. "P001"
    private String name;         // pet's name
    private String species;      // Cat, Dog, Rabbit, etc.
    private int ageInMonths;     // age counted in months
    private double price;        // selling price in RM
    private boolean vaccinated;  // has the pet had its shots?
    private boolean available;   // still in the shop (true) or already sold (false)?

    // ---------- Constructors ----------

    /** Default constructor: sets safe starting values so an object is never half empty. */
    public Pet() {
        this.petId = "P000";
        this.name = "Unnamed";
        this.species = "Unknown";
        this.ageInMonths = 0;
        this.price = 0.0;
        this.vaccinated = false;
        this.available = true;
    }

    /** Parameterized constructor: goes through the setters so validation is reused. */
    public Pet(String petId, String name, String species, int ageInMonths,
               double price, boolean vaccinated, boolean available) {
        setPetId(petId);
        setName(name);
        setSpecies(species);
        setAgeInMonths(ageInMonths);
        setPrice(price);
        setVaccinated(vaccinated);
        setAvailable(available);
    }

    // ---------- Getters ----------
    public String getPetId()      { return petId; }
    public String getName()       { return name; }
    public String getSpecies()    { return species; }
    public int getAgeInMonths()   { return ageInMonths; }
    public double getPrice()      { return price; }
    public boolean isVaccinated() { return vaccinated; }
    public boolean isAvailable()  { return available; }

    // ---------- Setters (with validation) ----------
    public void setPetId(String petId) {
        if (petId == null || petId.trim().isEmpty()) {
            throw new IllegalArgumentException("Pet ID cannot be empty.");
        }
        this.petId = petId.trim().toUpperCase();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Pet name cannot be empty.");
        }
        this.name = name.trim();
    }

    public void setSpecies(String species) {
        if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("Species cannot be empty.");
        }
        this.species = species.trim();
    }

    public void setAgeInMonths(int ageInMonths) {
        if (ageInMonths < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.ageInMonths = ageInMonths;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public void setVaccinated(boolean vaccinated) { this.vaccinated = vaccinated; }
    public void setAvailable(boolean available)   { this.available = available; }

    // ---------- Methods from Assignment 1 ----------

    /** Returns the price after taking off a percentage discount (0 to 100). */
    public double calculateDiscountedPrice(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            return price; // ignore a silly discount value
        }
        return price - (price * discountPercent / 100.0);
    }

    /** Pets younger than 6 months count as babies. */
    public boolean isBabyPet() {
        return ageInMonths < 6;
    }

    /**
     * A pet can only be sold if it is still available AND has been vaccinated.
     * Child classes may override this when the rule is different (for example fish).
     */
    public boolean canBeSold() {
        return available && vaccinated;
    }

    // ---------- Methods that the child classes OVERRIDE (polymorphism) ----------

    /**
     * Name of the kind of pet. Dog, Cat and Fish return their own label; for animals
     * that have no class of their own (rabbit, hamster...) it simply returns the species.
     */
    public String getPetType() {
        return species;
    }

    /** Extra details shown after the main line. The base class has nothing extra. */
    public String getDetails() {
        return "";
    }

    /** Generic sound for animals that do not have their own class (hamster, rabbit...). */
    @Override
    public String makeSound() {
        return "Squeak squeak";
    }

    /** Generic monthly care cost: a flat RM 30 for food and bedding. */
    @Override
    public double calculateMonthlyCareCost() {
        return 30.0;
    }

    @Override
    public String getCareTips() {
        return "Give fresh food and water every day and clean the cage weekly.";
    }

    /**
     * Prints the pet summary line. The extra line from getDetails() is different for
     * each type of pet, so this one method works for every child class.
     */
    public void displayInfo() {
        System.out.printf("[%s] %-8s %-10s %2d mth  RM%8.2f  Vaccinated: %-3s  Status: %s%n",
                petId, name, getPetType(), ageInMonths, price,
                vaccinated ? "Yes" : "No",
                available ? "Available" : "Sold");
        String details = getDetails();
        if (!details.isEmpty()) {
            System.out.println("        -> " + details);
        }
    }

    @Override
    public String toString() {
        return name + " (" + petId + ")";
    }
}
