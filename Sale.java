/**
 * Sale.java
 *
 * ADDITIONAL CLASS 2.
 * One completed purchase: it links a Customer to a Pet. This is the class where the
 * objects meet. When a Sale is created it works out the final price using the
 * customer's discount, marks the pet as sold and updates the customer's record.
 * Its fields are final, so a receipt can never be edited after the sale.
 */
public class Sale {

    /** Counter used to give every sale its own number. */
    private static int nextSaleNumber = 5001;

    private final int saleId;
    private final Customer customer;
    private final Pet pet;
    private final double discountPercent;
    private final double finalPrice;

    public Sale(Customer customer, Pet pet) {
        this.saleId = nextSaleNumber++;
        this.customer = customer;
        this.pet = pet;
        this.discountPercent = customer.getDiscountRate();             // read BEFORE the purchase
        this.finalPrice = pet.calculateDiscountedPrice(discountPercent);

        pet.setAvailable(false);               // mark as sold via the setter
        customer.addPurchase(finalPrice);      // update points, total spent and membership
    }

    public int getSaleId()             { return saleId; }
    public Customer getCustomer()      { return customer; }
    public Pet getPet()                { return pet; }
    public double getDiscountPercent() { return discountPercent; }
    public double getFinalPrice()      { return finalPrice; }

    @Override
    public String toString() {
        return String.format("Sale #%d | %-8s bought %-8s (%-7s) | RM %8.2f after %2.0f%% off",
                saleId, customer.getName(), pet.getName(), pet.getPetType(), finalPrice, discountPercent);
    }
}
