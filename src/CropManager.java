
import java.util.ArrayList;

public class CropManager {

    private FarmData farmData;

    // Constructor

    public CropManager(FarmData farmData) {
        if (farmData == null) {
            throw new IllegalArgumentException(
                "FarmData cannot be null."
            );
        }

        this.farmData = farmData;
    }

    // Add Crop

    public boolean addCrop(Crop crop) {
        if (crop == null) {
            System.out.println("Error: There is no crop to add.");
            return false;
        }

        if (findCrop(crop.getCropID()) != null) {
            System.out.println(
                "Error: Crop ID " + crop.getCropID() + " is already used."
            );
            return false;
        }

        farmData.addCrop(crop);

        System.out.println("Crop added: " + crop.getCropName());

        return true;
    }

    // View All Crops

    public void viewAllCrops() {
        ArrayList<Crop> crops = farmData.getCrops();

        if (crops.isEmpty()) {
            System.out.println("No crops yet.");
            return;
        }

        for (Crop crop : crops) {
            System.out.println(crop.getCropDetails());
            System.out.println("-----------------------");
        }

        System.out.println("Total crops: " + crops.size());
    }

    // Search Crop

    public Crop searchCrop(String cropID) {
        return findCrop(cropID);
    }

    // Update Crop Status

    public boolean updateCropStatus(String cropID, String status) {
        Crop crop = findCrop(cropID);

        if (crop == null) {
            System.out.println(
                "Error: Crop ID " + cropID + " was not found."
            );

            return false;
        }

        try {
            crop.setStatus(status);

            System.out.println(
                crop.getCropName() + " is now: " + crop.getStatus()
            );

            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Delete Crop

    public boolean deleteCrop(String cropID) {
        Crop crop = findCrop(cropID);

        if (crop == null) {
            System.out.println(
                "Error: Crop ID " + cropID + " was not found."
            );

            return false;
        }

        // Prevent deletion if existing records reference this crop.

        for (FarmRecord record : farmData.getAllRecords()) {
            if (record.getCropID().equalsIgnoreCase(cropID.trim())) {
                System.out.println(
                    "Cannot delete crop. Existing financial or harvest "
                    + "records are associated with it."
                );

                return false;
            }
        }

        farmData.getCrops().remove(crop);

        System.out.println("Crop deleted: " + crop.getCropName());

        return true;
    }

    // Find Crop Helper

    private Crop findCrop(String cropID) {
        if (cropID == null || cropID.trim().isEmpty()) {
            return null;
        }

        for (Crop crop : farmData.getCrops()) {
            if (crop.getCropID().equalsIgnoreCase(cropID.trim())) {
                return crop;
            }
        }

        return null;
    }
}
