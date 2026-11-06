
import java.util.ArrayList;

/**
 * SalesManager.java
 * -----------------
 * Manages sales records in AniTala.
 *
 * Responsibilities:
 * - Add sales records.
 * - View all sales.
 * - Search sales.
 * - Calculate total revenue.
 * - Calculate revenue per crop.
 */
public class SalesManager {

    private FarmData farmData;

    public SalesManager(FarmData farmData) {
        if (farmData == null) {
            throw new IllegalArgumentException(
                "SalesManager needs a valid FarmData object.");
        }

        this.farmData = farmData;
    }

    /**
     * Adds a sale after validating its information.
     */
    public void addSale(SalesRecord sale) {
        validateSale(sale);

        // Store through the shared FarmData object.
        farmData.addSale(sale);

        System.out.println("Sales record successfully added!");
    }

    /**
     * Displays all sales records.
     */
    public void viewAllSales() {
        ArrayList<SalesRecord> sales = farmData.getSales();

        if (sales.isEmpty()) {
            System.out.println("No sales records found.");
            return;
        }

        System.out.println("===== ALL SALES RECORDS =====");

        for (SalesRecord sale : sales) {
            sale.displayDetails();
        }
    }

    /**
     * Searches for a sale using its record ID.
     * Returns the matching record or null.
     */
    public SalesRecord searchSale(String recordID) {
        if (recordID == null || recordID.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Record ID cannot be empty.");
        }

        for (SalesRecord sale : farmData.getSales()) {
            if (sale.getRecordID().equalsIgnoreCase(recordID.trim())) {
                return sale;
            }
        }

        return null;
    }

    /**
     * Calculates revenue from all sales.
     */
    public double calculateTotalRevenue() {
        double total = 0.0;

        for (SalesRecord sale : farmData.getSales()) {
            total += sale.getFinancialImpact();
        }

        return total;
    }

    /**
     * Calculates revenue for one specific crop.
     */
    public double calculateRevenueByCrop(String cropID) {
        validateCropID(cropID);

        double total = 0.0;

        for (SalesRecord sale : farmData.getSales()) {
            if (sale.getCropID().equalsIgnoreCase(cropID.trim())) {
                total += sale.getFinancialImpact();
            }
        }

        return total;
    }

    // ---------- Helper methods ----------

    /**
     * Validates a sale before storing it.
     */
    private void validateSale(SalesRecord sale) {
        if (sale == null) {
            throw new IllegalArgumentException(
                "Sale record cannot be null.");
        }

        validateCropID(sale.getCropID());

        // Check global record ID uniqueness.
        for (FarmRecord existing : farmData.getAllRecords()) {
            if (existing.getRecordID().equalsIgnoreCase(
                    sale.getRecordID())) {
                throw new IllegalArgumentException(
                    "Record ID already exists in the system.");
            }
        }

        if (!Double.isFinite(sale.getQuantitySold())
                || sale.getQuantitySold() <= 0) {
            throw new IllegalArgumentException(
                "Quantity sold must be greater than zero.");
        }

        if (sale.getUnit() == null || sale.getUnit().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Unit cannot be empty.");
        }

        if (!Double.isFinite(sale.getPricePerUnit())
                || sale.getPricePerUnit() <= 0) {
            throw new IllegalArgumentException(
                "Price per unit must be greater than zero.");
        }
    }

    /**
     * Validates that a crop exists.
     */
    private void validateCropID(String cropID) {
        if (cropID == null || cropID.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Crop ID cannot be empty.");
        }

        boolean cropExists = false;

        for (Crop crop : farmData.getCrops()) {
            if (crop.getCropID().equalsIgnoreCase(cropID.trim())) {
                cropExists = true;
                break;
            }
        }

        if (!cropExists) {
            throw new IllegalArgumentException(
                "Crop ID does not exist: " + cropID);
        }
    }
}
