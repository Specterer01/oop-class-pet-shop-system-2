/**
 * Fish.java
 *
 * DERIVED CLASS 3 (inherits from Pet).
 * Adds water type and tank size, one unique method (isBeginnerFriendly) and its own
 * versions of the care methods. Fish are the one animal where the sale rule changes:
 * they cannot be vaccinated, so Fish overrides canBeSold().
 */
public class Fish extends Pet {

    /** True for saltwater fish, false for freshwater fish. */
    private boolean saltwater;
    /** Minimum tank size the fish needs, in litres. */
    private int tankSizeLitres;

    public Fish(String petId, String name, int ageInMonths, double price,
                boolean available, boolean saltwater, int tankSizeLitres) {
        super(petId, name, "Fish", ageInMonths, price, false, available); // fish are never vaccinated
        this.saltwater = saltwater;
        setTankSizeLitres(tankSizeLitres);
    }

    public boolean isSaltwater()    { return saltwater; }
    public int getTankSizeLitres()  { return tankSizeLitres; }

    public void setSaltwater(boolean saltwater) { this.saltwater = saltwater; }

    public void setTankSizeLitres(int tankSizeLitres) {
        if (tankSizeLitres <= 0) {
            throw new IllegalArgumentException("Tank size must be more than zero.");
        }
        this.tankSizeLitres = tankSizeLitres;
    }

    /** UNIQUE METHOD: freshwater fish are easier to keep, so they suit beginners. */
    public boolean isBeginnerFriendly() {
        return !saltwater;
    }

    @Override
    public String getPetType() { return "Fish"; }

    @Override
    public String getDetails() {
        return (saltwater ? "Saltwater" : "Freshwater") + ", needs a " + tankSizeLitres + "L tank";
    }

    /** OVERRIDDEN RULE: fish cannot be vaccinated, so only availability matters. */
    @Override
    public boolean canBeSold() {
        return isAvailable();
    }

    @Override
    public String makeSound() { return "(silent, only bubbles)"; }

    /**
     * Fish care: RM 15 food, plus RM 35 for sea salt and testing kits if saltwater,
     * plus RM 0.10 per litre of tank for water treatment.
     */
    @Override
    public double calculateMonthlyCareCost() {
        double cost = 15.0;
        if (saltwater) {
            cost += 35.0;
        }
        cost += tankSizeLitres * 0.10;
        return cost;
    }

    @Override
    public String getCareTips() {
        if (saltwater) {
            return "Test the salinity every week and change 10% of the water.";
        }
        return "Change a quarter of the tank water every two weeks.";
    }
}
