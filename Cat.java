/**
 * Cat.java
 *
 * DERIVED CLASS 2 (inherits from Pet).
 * Adds coat length and indoor/outdoor information, one unique method
 * (needsLitterTray) and its own versions of the care methods.
 */
public class Cat extends Pet {

    /** True for long haired cats that need regular grooming. */
    private boolean longHaired;
    /** True if the cat lives only inside the house. */
    private boolean indoorOnly;

    public Cat(String petId, String name, int ageInMonths, double price,
               boolean vaccinated, boolean available, boolean longHaired, boolean indoorOnly) {
        super(petId, name, "Cat", ageInMonths, price, vaccinated, available);
        this.longHaired = longHaired;
        this.indoorOnly = indoorOnly;
    }

    public boolean isLongHaired() { return longHaired; }
    public boolean isIndoorOnly() { return indoorOnly; }

    public void setLongHaired(boolean longHaired) { this.longHaired = longHaired; }
    public void setIndoorOnly(boolean indoorOnly) { this.indoorOnly = indoorOnly; }

    /** UNIQUE METHOD: indoor cats need a litter tray at home. */
    public boolean needsLitterTray() {
        return indoorOnly;
    }

    @Override
    public String getPetType() { return "Cat"; }

    @Override
    public String getDetails() {
        return (longHaired ? "Long haired" : "Short haired") + ", "
                + (indoorOnly ? "indoor only" : "can go outdoors");
    }

    @Override
    public String makeSound() { return "Meow~"; }

    /** Cat care: RM 60 food and litter, plus RM 25 grooming if the cat is long haired. */
    @Override
    public double calculateMonthlyCareCost() {
        double cost = 60.0;
        if (longHaired) {
            cost += 25.0;
        }
        return cost;
    }

    @Override
    public String getCareTips() {
        if (longHaired) {
            return "Brush the coat every day to stop tangles and hairballs.";
        }
        return "Keep the litter tray clean and give it a scratching post.";
    }
}
