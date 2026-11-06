
public class Farmer {

    private String farmerID;
    private String fullName;
    private String contactNumber;
    private String farmLocation;
    private double farmSize;

    public Farmer(String farmerID, String fullName, String contactNumber,
                  String farmLocation, double farmSize) {

        setFarmerID(farmerID);
        setFullName(fullName);
        setContactNumber(contactNumber);
        setFarmLocation(farmLocation);
        setFarmSize(farmSize);
    }

    // Getters

    public String getFarmerID() {
        return farmerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getFarmLocation() {
        return farmLocation;
    }

    public double getFarmSize() {
        return farmSize;
    }

    // Setters

    public void setFarmerID(String farmerID) {
        this.farmerID = checkNotEmpty(farmerID, "Farmer ID");
    }

    public void setFullName(String fullName) {
        this.fullName = checkNotEmpty(fullName, "Full name");
    }

    public void setContactNumber(String contactNumber) {
        String number = checkNotEmpty(contactNumber, "Contact number");

        // Remove spaces and dashes.
        number = number.replace(" ", "").replace("-", "");

        // Philippine mobile number validation.
        if (!number.matches("09[0-9]{9}")) {
            throw new IllegalArgumentException(
                "Contact number must have 11 digits and start with 09."
            );
        }

        this.contactNumber = number;
    }

    public void setFarmLocation(String farmLocation) {
        this.farmLocation = checkNotEmpty(farmLocation, "Farm location");
    }

    public void setFarmSize(double farmSize) {
        if (!Double.isFinite(farmSize) || farmSize <= 0) {
            throw new IllegalArgumentException(
                "Farm size must be a valid number greater than 0 hectares."
            );
        }

        this.farmSize = farmSize;
    }

    // Display Farmer Details

    public String getFarmerDetails() {
        return "Farmer ID      : " + farmerID + "\n"
             + "Full Name      : " + fullName + "\n"
             + "Contact Number : " + contactNumber + "\n"
             + "Farm Location  : " + farmLocation + "\n"
             + "Farm Size      : " + farmSize + " hectares";
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
