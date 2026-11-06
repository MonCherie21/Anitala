public class ExpenseRecord extends FarmRecord {

    private String category;
    private double amount;

    public ExpenseRecord(String recordID, String cropID, String date,
                         String description, String category, double amount) {

        super(recordID, cropID, date, description);

        setCategory(category);
        setAmount(amount);
    }

    @Override
    public void displayDetails() {
        System.out.println("[EXPENSE RECORD]");
        System.out.println("Record ID   : " + getRecordID());
        System.out.println("Crop ID     : " + getCropID());
        System.out.println("Date        : " + getDate());
        System.out.println("Description : " + getDescription());
        System.out.println("Category    : " + category);
        System.out.printf("Amount      : PHP %,.2f%n", amount);
        System.out.println("---------------------------------------------");
    }

    @Override
    public double getFinancialImpact() {
        return -amount;
    }

    // Getters

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    // Setters

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Expense category cannot be empty."
            );
        }

        this.category = category.trim();
    }

    public void setAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                "Expense amount must be greater than zero."
            );
        }

        this.amount = amount;
    }
}