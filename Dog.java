/**
 * Dog.java
 *
 * DERIVED CLASS 1 (inherits from Pet).
 * Adds the breed and whether the dog is house trained, one unique method
 * (needsTraining) and its own versions of the care methods.
 */
public class Dog extends Pet {

    /** Breed of the dog, for example "Labrador". */
    private String breed;
    /** True if the dog already knows where to do its business. */
    private boolean houseTrained;

    public Dog(String petId, String name, int ageInMonths, double price,
               boolean vaccinated, boolean available, String breed, boolean houseTrained) {
        super(petId, name, "Dog", ageInMonths, price, vaccinated, available); // parent sets shared data
        setBreed(breed);
        this.houseTrained = houseTrained;
    }

    public String getBreed()          { return breed; }
    public boolean isHouseTrained()   { return houseTrained; }

    public void setBreed(String breed) {
        if (breed == null || breed.trim().isEmpty()) {
            throw new IllegalArgumentException("Breed cannot be empty.");
        }
        this.breed = breed.trim();
    }

    public void setHouseTrained(boolean houseTrained) {
        this.houseTrained = houseTrained;
    }

    /** UNIQUE METHOD: dogs that are not house trained need training classes. */
    public boolean needsTraining() {
        return !houseTrained;
    }

    @Override
    public String getPetType() { return "Dog"; }

    @Override
    public String getDetails() {
        return "Breed: " + breed + ", house trained: " + (houseTrained ? "yes" : "no");
    }

    @Override
    public String makeSound() { return "Woof! Woof!"; }

    /**
     * Dog care: RM 90 food and RM 20 vet check. Puppies (baby pets) need an extra
     * RM 25 for puppy milk and booster visits.
     */
    @Override
    public double calculateMonthlyCareCost() {
        double cost = 90.0 + 20.0;
        if (isBabyPet()) {
            cost += 25.0;
        }
        return cost;
    }

    @Override
    public String getCareTips() {
        if (needsTraining()) {
            return "Needs house training. Take it out after every meal and book a training class.";
        }
        return "Walk it at least once a day and keep up its yearly vaccinations.";
    }
}
