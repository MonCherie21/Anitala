
import java.util.ArrayList;

public class HarvestManager {

    private FarmData farmData;

    public HarvestManager(FarmData farmData) {
        if (farmData == null) {
            throw new IllegalArgumentException(
                "HarvestManager needs a valid FarmData object.");
        }

        this.farmData = farmData;
    }

    public void addHarvest(HarvestRecord harvest) {
        if (harvest == null) {
            throw new IllegalArgumentException(
                "Harvest record cannot be null.");
        }

        validateCropID(harvest.getCropID());

        // Check all record types for duplicate IDs.
        for (FarmRecord existing : farmData.getAllRecords()) {
            if (existing.getRecordID().equalsIgnoreCase(
                    harvest.getRecordID())) {
                throw new IllegalArgumentException(
                    "Record ID already exists in the system.");
            }
        }

        farmData.addHarvest(harvest);

        System.out.println("Harvest record successfully added!");
    }

    public void viewAllHarvests() {
        ArrayList<HarvestRecord> harvests = farmData.getHarvests();

        if (harvests.isEmpty()) {
            System.out.println("No harvest records available.");
            return;
        }

        System.out.println("\n========== HARVEST HISTORY ==========");

        for (HarvestRecord harvest : harvests) {
            harvest.displayDetails();
        }

        System.out.println("=====================================\n");
    }

    public void searchHarvest(String recordID) {
        if (recordID == null || recordID.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Record ID cannot be empty.");
        }

        for (HarvestRecord harvest : farmData.getHarvests()) {
            if (harvest.getRecordID().equalsIgnoreCase(recordID.trim())) {
                System.out.println("Harvest record found!");
                harvest.displayDetails();
                return;
            }
        }

        System.out.println(
            "No harvest record found with ID: " + recordID);
    }

    public String calculateTotalHarvest(String cropID) {
        validateCropID(cropID);

        double totalKg = 0.0;
        double totalSacks = 0.0;

        for (HarvestRecord harvest : farmData.getHarvests()) {
            if (harvest.getCropID().equalsIgnoreCase(cropID.trim())) {

                if (harvest.getUnit().equalsIgnoreCase("kg")) {
                    totalKg += harvest.getQuantity();
                } else if (harvest.getUnit().equalsIgnoreCase("sacks")) {
                    totalSacks += harvest.getQuantity();
                }
            }
        }

        return String.format(
            "===== TOTAL HARVEST FOR %s =====%n" +
            "Total Kilograms : %.2f kg%n" +
            "Total Sacks     : %.2f sacks%n" +
            "================================",
            cropID, totalKg, totalSacks);
    }

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
