
import java.util.ArrayList;
import java.util.Locale;

public class FinancialReport {

    private FarmData farmData;

    // Constructor
    public FinancialReport(FarmData farmData) {

        if (farmData == null) {
            throw new IllegalArgumentException(
                "FarmData cannot be null."
            );
        }

        this.farmData = farmData;
    }

    // =====================================
    // CALCULATE TOTAL EXPENSES BY CROP
    // =====================================

    public double calculateTotalExpenses(String cropID) {

        validateCropID(cropID);

        double total = 0.0;

        for (FarmRecord record : farmData.getAllRecords()) {

            if (record.getCropID().equals(cropID)
                    && record.getFinancialImpact() < 0) {

                total += Math.abs(record.getFinancialImpact());
            }
        }

        return total;
    }

    // =====================================
    // CALCULATE GROSS REVENUE BY CROP
    // =====================================

    public double calculateGrossRevenue(String cropID) {

        validateCropID(cropID);

        double total = 0.0;

        for (FarmRecord record : farmData.getAllRecords()) {

            if (record.getCropID().equals(cropID)
                    && record.getFinancialImpact() > 0) {

                total += record.getFinancialImpact();
            }
        }

        return total;
    }

    // =====================================
    // CALCULATE NET PROFIT BY CROP
    // =====================================

    public double calculateNetProfit(String cropID) {

        double revenue = calculateGrossRevenue(cropID);
        double expenses = calculateTotalExpenses(cropID);

        return revenue - expenses;
    }

    // =====================================
    // GENERATE REPORT FOR ONE CROP
    // =====================================

    public String generateCropReport(String cropID) {

        validateCropID(cropID);

        StringBuilder report = new StringBuilder();

        report.append("\n========================================\n");
        report.append("       ANITALA CROP FINANCIAL REPORT\n");
        report.append("========================================\n");

        report.append("Crop ID: ").append(cropID).append("\n");

        boolean found = false;

        // Display all records belonging to this crop
        for (FarmRecord record : farmData.getAllRecords()) {

            if (record.getCropID().equals(cropID)) {

                found = true;

                report.append("\nRecord ID: ")
                      .append(record.getRecordID()).append("\n");

                report.append("Date: ")
                      .append(record.getDate()).append("\n");

                report.append("Description: ")
                      .append(record.getDescription()).append("\n");

                report.append("Financial Impact: PHP ")
                      .append(String.format(
                          Locale.US, "%,.2f",
                          record.getFinancialImpact()
                      ))
                      .append("\n");

                report.append("----------------------------------------\n");
            }
        }

        if (!found) {
            return "No records found for crop ID: " + cropID;
        }

        // Calculate financial totals
        double expenses = calculateTotalExpenses(cropID);
        double revenue = calculateGrossRevenue(cropID);
        double profit = calculateNetProfit(cropID);

        report.append("\n========== FINANCIAL SUMMARY ==========\n");

        report.append(String.format(
            Locale.US, "Total Expenses : PHP %,.2f%n", expenses
        ));

        report.append(String.format(
            Locale.US, "Gross Revenue  : PHP %,.2f%n", revenue
        ));

        report.append(String.format(
            Locale.US, "Net Profit     : PHP %,.2f%n", profit
        ));

        report.append("========================================\n");

        return report.toString();
    }

    // =====================================
    // GENERATE OVERALL FARM REPORT
    // =====================================

    public String generateOverallReport() {

        ArrayList<FarmRecord> allRecords =
            farmData.getAllRecords();

        if (allRecords.isEmpty()) {
            return "No farm records available yet.";
        }

        double totalExpenses = 0.0;
        double totalRevenue = 0.0;

        // Runtime polymorphism
        for (FarmRecord record : allRecords) {

            double impact = record.getFinancialImpact();

            if (impact < 0) {

                totalExpenses += Math.abs(impact);

            } else if (impact > 0) {

                totalRevenue += impact;
            }

            // A zero impact means no financial change.
            // HarvestRecord has zero financial impact.
        }

        double netProfit = totalRevenue - totalExpenses;

        StringBuilder report = new StringBuilder();

        report.append("\n========================================\n");
        report.append("      ANITALA OVERALL FARM REPORT\n");
        report.append("========================================\n");

        report.append(String.format(
            Locale.US, "Total Records   : %d%n", allRecords.size()
        ));

        report.append(String.format(
            Locale.US, "Total Expenses  : PHP %,.2f%n", totalExpenses
        ));

        report.append(String.format(
            Locale.US, "Total Revenue   : PHP %,.2f%n", totalRevenue
        ));

        report.append("----------------------------------------\n");

        report.append(String.format(
            Locale.US, "Net Profit      : PHP %,.2f%n", netProfit
        ));

        report.append("========================================\n");

        return report.toString();
    }

    // =====================================
    // VALIDATE CROP ID
    // =====================================

    private void validateCropID(String cropID) {

        if (cropID == null || cropID.trim().isEmpty()) {

            throw new IllegalArgumentException(
                "Crop ID cannot be empty."
            );
        }

        boolean cropExists = false;

        for (Crop crop : farmData.getCrops()) {

            if (crop.getCropID().equals(cropID)) {

                cropExists = true;
                break;
            }
        }

        if (!cropExists) {

            throw new IllegalArgumentException(
                "Crop ID does not exist: " + cropID
            );
        }
    }
}
