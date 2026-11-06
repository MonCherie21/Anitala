
import java.util.ArrayList;

public class FarmData {

    private Farmer farmer;

    private ArrayList<Crop> crops;
    private ArrayList<ExpenseRecord> expenses;
    private ArrayList<HarvestRecord> harvests;
    private ArrayList<SalesRecord> sales;
    private ArrayList<FarmRecord> allRecords;

    public FarmData() {
        farmer = null;

        crops = new ArrayList<>();
        expenses = new ArrayList<>();
        harvests = new ArrayList<>();
        sales = new ArrayList<>();
        allRecords = new ArrayList<>();
    }

    // ---------- Farmer ----------

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        if (farmer == null) {
            throw new IllegalArgumentException(
                "Farmer cannot be null.");
        }

        this.farmer = farmer;
    }

    // ---------- Crops ----------

    public void addCrop(Crop crop) {
        if (crop == null) {
            throw new IllegalArgumentException(
                "Crop cannot be null.");
        }

        crops.add(crop);
    }

    public ArrayList<Crop> getCrops() {
        return crops;
    }

    // ---------- Expenses ----------

    public void addExpense(ExpenseRecord expense) {
        if (expense == null) {
            throw new IllegalArgumentException(
                "Expense record cannot be null.");
        }

        validateUniqueRecordID(expense.getRecordID());

        expenses.add(expense);
        allRecords.add(expense);
    }

    public ArrayList<ExpenseRecord> getExpenses() {
        return expenses;
    }

    // ---------- Harvests ----------

    public void addHarvest(HarvestRecord harvest) {
        if (harvest == null) {
            throw new IllegalArgumentException(
                "Harvest record cannot be null.");
        }

        validateUniqueRecordID(harvest.getRecordID());

        harvests.add(harvest);
        allRecords.add(harvest);
    }

    public ArrayList<HarvestRecord> getHarvests() {
        return harvests;
    }

    // ---------- Sales ----------

    public void addSale(SalesRecord sale) {
        if (sale == null) {
            throw new IllegalArgumentException(
                "Sales record cannot be null.");
        }

        validateUniqueRecordID(sale.getRecordID());

        sales.add(sale);
        allRecords.add(sale);
    }

    public ArrayList<SalesRecord> getSales() {
        return sales;
    }

    // ---------- Combined Records ----------

    public ArrayList<FarmRecord> getAllRecords() {
        return allRecords;
    }

    // ---------- Validation ----------

    private void validateUniqueRecordID(String recordID) {
        if (recordID == null || recordID.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Record ID cannot be empty.");
        }

        for (FarmRecord existing : allRecords) {
            if (existing.getRecordID().equalsIgnoreCase(
                    recordID.trim())) {
                throw new IllegalArgumentException(
                    "Record ID already exists in the system.");
            }
        }
    }
}
