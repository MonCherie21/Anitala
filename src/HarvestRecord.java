
/**
 * HarvestRecord.java
 * ------------------
 * Represents harvested crops in AniTala.
 * Extends FarmRecord to inherit the common farm record attributes.
 *
 * OOP PRINCIPLES:
 *
 * INHERITANCE
 *   - Extends FarmRecord and inherits its common attributes.
 *
 * ENCAPSULATION
 *   - quantity, unit, and quality are private.
 *   - Getters provide controlled access to the attributes.
 *
 * POLYMORPHISM
 *   - Overrides displayDetails() and getFinancialImpact()
 *     from the FarmRecord parent class.
 *
 * ABSTRACTION
 *   - Implements the abstract methods required by FarmRecord.
 */
public class HarvestRecord extends FarmRecord {

    private double quantity;
    private String unit;
    private String quality;

    /**
     * Constructor for creating a harvest record.
     */
    public HarvestRecord(String recordID, String cropID, String date,
            String description, double quantity, String unit, String quality) {

        super(recordID, cropID, date, description);

        if (quantity <= 0 || Double.isNaN(quantity) || Double.isInfinite(quantity)) {
            throw new IllegalArgumentException(
                "Harvest quantity must be greater than zero.");
        }

        if (unit == null ||
            !(unit.trim().equalsIgnoreCase("kg") ||
              unit.trim().equalsIgnoreCase("sacks"))) {
            throw new IllegalArgumentException(
                "Unit must be kg or sacks.");
        }

        if (quality == null ||
            !(quality.trim().equalsIgnoreCase("Premium") ||
              quality.trim().equalsIgnoreCase("Standard") ||
              quality.trim().equalsIgnoreCase("Low Grade"))) {
            throw new IllegalArgumentException(
                "Quality must be Premium, Standard, or Low Grade.");
        }

        this.quantity = quantity;
        this.unit = unit.trim().equalsIgnoreCase("kg") ? "kg" : "sacks";

        String enteredQuality = quality.trim();

        if (enteredQuality.equalsIgnoreCase("Premium")) {
            this.quality = "Premium";
        } else if (enteredQuality.equalsIgnoreCase("Standard")) {
            this.quality = "Standard";
        } else {
            this.quality = "Low Grade";
        }
    }

    /**
     * Returns the harvested quantity.
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Returns the harvest unit.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Returns the harvest quality.
     */
    public String getQuality() {
        return quality;
    }

    /**
     * Displays complete harvest information.
     */
    @Override
    public void displayDetails() {
        System.out.println("\n===== HARVEST DETAILS =====");
        System.out.println("Record ID   : " + getRecordID());
        System.out.println("Crop ID     : " + getCropID());
        System.out.println("Date        : " + getDate());
        System.out.println("Description : " + getDescription());
        System.out.println("Quantity    : " + quantity + " " + unit);
        System.out.println("Quality     : " + quality);
        System.out.println("Financial Impact : PHP 0.00");
        System.out.println("===========================\n");
    }

    /**
     * Harvesting alone does not generate revenue or expenses.
     */
    @Override
    public double getFinancialImpact() {
        return 0.0;
    }
}
