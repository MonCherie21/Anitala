
import java.time.LocalDate;

public class Crop {

    // Allowed crop statuses.

    public static final String GROWING = "Growing";
    public static final String READY_FOR_HARVEST = "Ready for Harvest";
    public static final String HARVESTED = "Harvested";

    // Attributes

    private String cropID;
    private String cropName;
    private String variety;
    private LocalDate plantingDate;
    private LocalDate expectedHarvestDate;
    private double areaPlanted;
    private String status;

    // Constructor

    public Crop(String cropID, String cropName, String variety,
                LocalDate plantingDate, LocalDate expectedHarvestDate,
                double areaPlanted, String status) {

        setCropID(cropID);
        setCropName(cropName);
        setVariety(variety);
        setPlantingDate(plantingDate);
        setExpectedHarvestDate(expectedHarvestDate);
        setAreaPlanted(areaPlanted);
        setStatus(status);
    }

    // Getters

    public String getCropID() {
        return cropID;
    }

    public String getCropName() {
        return cropName;
    }

    public String getVariety() {
        return variety;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public LocalDate getExpectedHarvestDate() {
        return expectedHarvestDate;
    }

    public double getAreaPlanted() {
        return areaPlanted;
    }

    public String getStatus() {
        return status;
    }

    // Setters

    public void setCropID(String cropID) {
        this.cropID = checkNotEmpty(cropID, "Crop ID");
    }

    public void setCropName(String cropName) {
        this.cropName = checkNotEmpty(cropName, "Crop name");
    }

    public void setVariety(String variety) {
        this.variety = checkNotEmpty(variety, "Variety");
    }

    public void setPlantingDate(LocalDate plantingDate) {
        if (plantingDate == null) {
            throw new IllegalArgumentException(
                "Planting date cannot be empty."
            );
        }

        if (expectedHarvestDate != null &&
            plantingDate.isAfter(expectedHarvestDate)) {

            throw new IllegalArgumentException(
                "Planting date cannot be after the harvest date."
            );
        }

        this.plantingDate = plantingDate;
    }

    public void setExpectedHarvestDate(LocalDate expectedHarvestDate) {
        if (expectedHarvestDate == null) {
            throw new IllegalArgumentException(
                "Expected harvest date cannot be empty."
            );
        }

        if (plantingDate != null &&
            expectedHarvestDate.isBefore(plantingDate)) {

            throw new IllegalArgumentException(
                "Harvest date cannot be before the planting date."
            );
        }

        this.expectedHarvestDate = expectedHarvestDate;
    }

    public void setAreaPlanted(double areaPlanted) {
        if (!Double.isFinite(areaPlanted) || areaPlanted <= 0) {
            throw new IllegalArgumentException(
                "Area planted must be a valid number greater than 0 hectares."
            );
        }

        this.areaPlanted = areaPlanted;
    }

    public void setStatus(String status) {
        String word = checkNotEmpty(status, "Status");

        if (word.equalsIgnoreCase(GROWING)) {
            this.status = GROWING;

        } else if (word.equalsIgnoreCase(READY_FOR_HARVEST)) {
            this.status = READY_FOR_HARVEST;

        } else if (word.equalsIgnoreCase(HARVESTED)) {
            this.status = HARVESTED;

        } else {
            throw new IllegalArgumentException(
                "Status must be Growing, Ready for Harvest, or Harvested."
            );
        }
    }

    // Display Crop Details

    public String getCropDetails() {
        return "Crop ID          : " + cropID + "\n"
             + "Crop Name        : " + cropName + "\n"
             + "Variety          : " + variety + "\n"
             + "Planting Date    : " + plantingDate + "\n"
             + "Expected Harvest : " + expectedHarvestDate + "\n"
             + "Area Planted     : " + areaPlanted + " hectares\n"
             + "Status           : " + status;
    }

    // Validation Helper

    private String checkNotEmpty(String text, String fieldName) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException(
                fieldName + " cannot be empty."
            );
        }

        return text.trim();
    }
}
