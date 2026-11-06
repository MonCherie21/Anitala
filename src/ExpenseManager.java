import java.util.ArrayList;

public class ExpenseManager {

    private FarmData farmData;

    public ExpenseManager(FarmData farmData) {
        if (farmData == null) {
            throw new IllegalArgumentException(
                "ExpenseManager needs a valid FarmData object."
            );
        }

        this.farmData = farmData;
    }

    // Add Expense

    public void addExpense(ExpenseRecord expense) {
        validateExpense(expense);

        farmData.addExpense(expense);

        System.out.println("Expense record added successfully!");
    }

    // View All Expenses

    public void viewAllExpenses() {
        ArrayList<ExpenseRecord> expenses = farmData.getExpenses();

        if (expenses.isEmpty()) {
            System.out.println("No expense records found.");
            return;
        }

        System.out.println("===== ALL EXPENSE RECORDS =====");

        for (ExpenseRecord expense : expenses) {
            expense.displayDetails();
        }
    }

    // Search Expense

    public ExpenseRecord searchExpense(String recordID) {
        if (recordID == null || recordID.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Record ID cannot be empty."
            );
        }

        for (ExpenseRecord expense : farmData.getExpenses()) {
            if (expense.getRecordID().equalsIgnoreCase(recordID.trim())) {
                return expense;
            }
        }

        return null;
    }

    // Calculate Total Expenses

    public double calculateTotalExpenses() {
        double total = 0.0;

        for (ExpenseRecord expense : farmData.getExpenses()) {
            total += expense.getAmount();
        }

        return total;
    }

    // Calculate Expenses by Crop

    public double calculateExpensesByCrop(String cropID) {
        if (cropID == null || cropID.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Crop ID cannot be empty."
            );
        }

        double total = 0.0;

        for (ExpenseRecord expense : farmData.getExpenses()) {
            if (expense.getCropID().equalsIgnoreCase(cropID.trim())) {
                total += expense.getAmount();
            }
        }

        return total;
    }

    // Validation Helpers

    private void validateExpense(ExpenseRecord expense) {

        if (expense == null) {
            throw new IllegalArgumentException(
                "Expense record cannot be null."
            );
        }

        // Check if Crop ID exists

        boolean cropExists = false;

        for (Crop crop : farmData.getCrops()) {
            if (crop.getCropID().equalsIgnoreCase(expense.getCropID())) {
                cropExists = true;
                break;
            }
        }

        if (!cropExists) {
            throw new IllegalArgumentException(
                "Crop ID does not exist."
            );
        }

        // Check global Record ID uniqueness

        for (FarmRecord existing : farmData.getAllRecords()) {
            if (existing.getRecordID().equalsIgnoreCase(expense.getRecordID())) {
                throw new IllegalArgumentException(
                    "Record ID " + expense.getRecordID() + " already exists."
                );
            }
        }

        if (!Double.isFinite(expense.getAmount()) ||
            expense.getAmount() <= 0) {

            throw new IllegalArgumentException(
                "Expense amount must be greater than zero."
            );
        }
    }
}