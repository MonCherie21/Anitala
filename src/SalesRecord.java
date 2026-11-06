
/**
 * SalesRecord.java
 * ----------------
 * Represents one sale of harvested crop.
 * Extends FarmRecord to inherit common farm record attributes.
 */
public class SalesRecord extends FarmRecord {

    private double quantitySold;
    private String unit;
    private double pricePerUnit;

    public SalesRecord(String recordID, String cropID, String date,
                       String description, double quantitySold,
                       String unit, double pricePerUnit) {

        super(recordID, cropID, date, description);

        setQuantitySold(quantitySold);
        setUnit(unit);
        setPricePerUnit(pricePerUnit);
    }

    /**
     * Calculates revenue from the sale.
     */
    public double calculateRevenue() {
        return quantitySold * pricePerUnit;
    }

    /**
     * Displays complete sales information.
     */
    @Override
    public void displayDetails() {
        System.out.println("[SALE RECORD]");
        System.out.println("Record ID    : " + getRecordID());
        System.out.println("Crop ID      : " + getCropID());
        System.out.println("Date         : " + getDate());
        System.out.println("Description  : " + getDescription());
        System.out.println("Quantity Sold: " + quantitySold + " " + unit);
        System.out.println(String.format(
            "Price/Unit   : PHP %,.2f", pricePerUnit));
        System.out.println(String.format(
            "Revenue      : PHP %,.2f", calculateRevenue()));
        System.out.println("---------------------------------------------");
    }

    /**
     * A sale adds money to the farmer's income.
     */
    @Override
    public double getFinancialImpact() {
        return calculateRevenue();
    }

    // ---------- Getters ----------

    public double getQuantitySold() {
        return quantitySold;
    }

    public String getUnit() {
        return unit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    // ---------- Setters with validation ----------

    public void setQuantitySold(double quantitySold) {
        if (!Double.isFinite(quantitySold) || quantitySold <= 0) {
            throw new IllegalArgumentException(
                "Quantity sold must be greater than zero.");
        }

        this.quantitySold = quantitySold;
    }

    public void setUnit(String unit) {
        if (unit == null || unit.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Unit cannot be empty.");
        }

        this.unit = unit.trim();
    }

    public void setPricePerUnit(double pricePerUnit) {
        if (!Double.isFinite(pricePerUnit) || pricePerUnit <= 0) {
            throw new IllegalArgumentException(
                "Price per unit must be greater than zero.");
        }

        this.pricePerUnit = pricePerUnit;
    }
}
