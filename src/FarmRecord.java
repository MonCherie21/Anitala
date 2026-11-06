
public abstract class FarmRecord {

    private String recordID;
    private String cropID;
    private String date;
    private String description;

    // Constructor

    public FarmRecord(String recordID, String cropID,
                      String date, String description) {

        if (recordID == null || recordID.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Record ID cannot be empty."
            );
        }

        if (cropID == null || cropID.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Crop ID cannot be empty."
            );
        }

        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Date cannot be empty."
            );
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Description cannot be empty."
            );
        }

        this.recordID = recordID.trim();
        this.cropID = cropID.trim();
        this.date = date.trim();
        this.description = description.trim();
    }

    // Abstract Methods

    public abstract void displayDetails();

    public abstract double getFinancialImpact();

    // Getters

    public String getRecordID() {
        return recordID;
    }

    public String getCropID() {
        return cropID;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
