/**
 * Careable.java
 *
 * COMMON INTERFACE (used for polymorphism).
 * Every animal in the shop needs looking after, but each kind of animal is looked
 * after differently. This interface lists what every animal MUST be able to do.
 * The method signatures are fixed here, and each class decides how to implement them.
 * The rest of the program can then treat a Dog, a Cat, a Fish or a plain Pet in
 * exactly the same way.
 */
public interface Careable {

    /**
     * The sound the animal makes (shown on the pet's "care card").
     *
     * @return a short text describing the sound
     */
    String makeSound();

    /**
     * Estimated cost of looking after the animal for one month (food, grooming, supplies).
     *
     * @return the monthly care cost in RM
     */
    double calculateMonthlyCareCost();

    /**
     * One short piece of advice for the new owner.
     *
     * @return the care tip
     */
    String getCareTips();
}
