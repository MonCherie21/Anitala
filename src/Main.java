
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {

    private static FarmData farmData;
    private static CropManager cropManager;
    private static ExpenseManager expenseManager;
    private static HarvestManager harvestManager;
    private static SalesManager salesManager;
    private static FinancialReport financialReport;

    public static void main(String[] args) {
        runApplication();
    }

    public static void runApplication() {

        // Initialize shared data.
        farmData = new FarmData();

        // Initialize managers using the SAME FarmData object.
        cropManager = new CropManager(farmData);
        expenseManager = new ExpenseManager(farmData);
        harvestManager = new HarvestManager(farmData);
        salesManager = new SalesManager(farmData);
        financialReport = new FinancialReport(farmData);

        System.out.println("================================");
        System.out.println("       WELCOME TO ANITALA");
        System.out.println("    FARM MANAGEMENT SYSTEM");
        System.out.println("================================");

        while (true) {
            displayMainMenu();

            int choice = InputHelper.readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    farmerMenu();
                    break;

                case 2:
                    cropMenu();
                    break;

                case 3:
                    expenseMenu();
                    break;

                case 4:
                    harvestMenu();
                    break;

                case 5:
                    salesMenu();
                    break;

                case 6:
                    reportMenu();
                    break;

                case 0:
                    System.out.println(
                        "Thank you for using AniTala!");
                    return;

                default:
                    System.out.println("Invalid menu choice.");
            }
        }
    }

    // ---------- Main Menu ----------

    public static void displayMainMenu() {
        System.out.println("\n========== ANITALA MAIN MENU ==========");
        System.out.println("1. Farmer Management");
        System.out.println("2. Crop Management");
        System.out.println("3. Expense Management");
        System.out.println("4. Harvest Management");
        System.out.println("5. Sales Management");
        System.out.println("6. Financial Reports");
        System.out.println("0. Exit");
        System.out.println("=======================================");
    }


    // ---------- Farmer Menu ----------

    private static void farmerMenu() {

        System.out.println("\n========== FARMER MANAGEMENT ==========");
        System.out.println("1. Register Farmer");
        System.out.println("2. View All Farmers");
        System.out.println("0. Back");

        int choice = InputHelper.readInt("Enter choice: ");

        switch (choice) {

            case 1:
                try {
                    String farmerID =
                        InputHelper.readString("Farmer ID: ");

                    String fullName =
                        InputHelper.readString("Full Name: ");

                    String contactNumber =
                        InputHelper.readString("Contact Number: ");

                    String farmLocation =
                        InputHelper.readString("Farm Location: ");

                    double farmSize =
                        InputHelper.readDouble("Farm Size: ");

                    Farmer farmer = new Farmer(
                        farmerID,
                        fullName,
                        contactNumber,
                        farmLocation,
                        farmSize
                    );

                    farmData.addFarmer(farmer);

                    System.out.println(
                        "Farmer registered successfully!");

                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 2:
                if (farmData.getFarmers().isEmpty()) {
                    System.out.println(
                        "No farmers have been registered.");
                } else {
                    System.out.println("\n========== REGISTERED FARMERS ==========");

                    for (Farmer farmer : farmData.getFarmers()) {
                        System.out.println(farmer.getFarmerDetails());
                        System.out.println("----------------------------------------");
                    }
                }

                System.out.println("\nPress Enter to continue...");
                InputHelper.readString("");
                break;

            case 0:
                return;

            default:
                System.out.println("Invalid choice.");
        }
    }


    // ---------- Crop Menu ----------

    private static void cropMenu() {

        System.out.println("\n========== CROP MANAGEMENT ==========");
        System.out.println("1. Add Crop");
        System.out.println("2. View All Crops");
        System.out.println("3. Search Crop");
        System.out.println("4. Update Crop Status");
        System.out.println("5. Delete Crop");
        System.out.println("0. Back");

        int choice = InputHelper.readInt("Enter choice: ");

        try {
            switch (choice) {

                case 1:
                    String cropID =
                        InputHelper.readString("Crop ID: ");

                    String cropName =
                        InputHelper.readString("Crop Name: ");

                    String variety =
                        InputHelper.readString("Variety: ");

                    LocalDate plantingDate = LocalDate.parse(
                        InputHelper.readString(
                            "Planting Date (YYYY-MM-DD): ")
                    );

                    LocalDate expectedHarvestDate = LocalDate.parse(
                        InputHelper.readString(
                            "Expected Harvest Date (YYYY-MM-DD): ")
                    );

                    double areaPlanted =
                        InputHelper.readDouble("Area Planted: ");

                    String status =
                        InputHelper.readString("Status: ");

                    Crop crop = new Crop(
                        cropID,
                        cropName,
                        variety,
                        plantingDate,
                        expectedHarvestDate,
                        areaPlanted,
                        status
                    );

                    cropManager.addCrop(crop);
                    break;

                case 2:
                    cropManager.viewAllCrops();
                    System.out.println("\nPress Enter to continue...");
                    InputHelper.readString("");
                    break;

                case 3:
                    String searchID =
                        InputHelper.readString("Enter Crop ID: ");

                    Crop foundCrop = cropManager.searchCrop(searchID);

                    if (foundCrop != null) {
                        System.out.println(foundCrop.getCropDetails());
                    } else {
                        System.out.println("Crop not found.");
                    }
                    System.out.println("\nPress Enter to continue...");
                    InputHelper.readString("");
                    break;

                case 4:
                    String updateID =
                        InputHelper.readString("Enter Crop ID: ");

                    String newStatus =
                        InputHelper.readString("New Status: ");

                    cropManager.updateCropStatus(updateID, newStatus);
                    break;

                case 5:
                    String deleteID =
                        InputHelper.readString("Enter Crop ID: ");

                    cropManager.deleteCrop(deleteID);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }

        } catch (DateTimeParseException e) {
            System.out.println(
                "Error: Invalid date format. Please use YYYY-MM-DD."
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------- Expense Menu ----------

    private static void expenseMenu() {

        System.out.println("\n========== EXPENSE MANAGEMENT ==========");
        System.out.println("1. Add Expense");
        System.out.println("2. View All Expenses");
        System.out.println("3. Search Expense");
        System.out.println("4. View Total Expenses");
        System.out.println("5. View Expenses By Crop");
        System.out.println("0. Back");

        int choice = InputHelper.readInt("Enter choice: ");

        try {
            switch (choice) {

                case 1:
                    String recordID =
                        InputHelper.readString("Record ID: ");

                    String cropID =
                        InputHelper.readString("Crop ID: ");

                    String date =
                        InputHelper.readString("Date: ");

                    String description =
                        InputHelper.readString("Description: ");

                    String category =
                        InputHelper.readString("Category: ");

                    double amount =
                        InputHelper.readDouble("Amount: ");

                    ExpenseRecord expense = new ExpenseRecord(
                        recordID, cropID, date,
                        description, category, amount
                    );

                    expenseManager.addExpense(expense);
                    break;

                case 2:
                    expenseManager.viewAllExpenses();
                    System.out.println("\nPress Enter to continue...");
                    InputHelper.readString("");
                    break;

                case 3:
                    String expenseID =
                        InputHelper.readString("Record ID: ");

                    ExpenseRecord foundExpense =
                        expenseManager.searchExpense(expenseID);

                    if (foundExpense != null) {
                        foundExpense.displayDetails();
                    } else {
                        System.out.println("Expense record not found.");
                    }
                    break;

                case 4:
                    System.out.printf(
                        "Total Expenses: PHP %,.2f%n",
                        expenseManager.calculateTotalExpenses());
                    break;

                case 5:
                    String expenseCropID =
                        InputHelper.readString("Crop ID: ");

                    System.out.printf(
                        "Total Expenses: PHP %,.2f%n",
                        expenseManager.calculateExpensesByCrop(
                            expenseCropID));
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------- Harvest Menu ----------

    private static void harvestMenu() {

        System.out.println("\n========== HARVEST MANAGEMENT ==========");
        System.out.println("1. Add Harvest");
        System.out.println("2. View All Harvests");
        System.out.println("3. Search Harvest");
        System.out.println("4. Calculate Total Harvest");
        System.out.println("0. Back");

        int choice = InputHelper.readInt("Enter choice: ");

        try {
            switch (choice) {

                case 1:
                    String recordID =
                        InputHelper.readString("Record ID: ");

                    String cropID =
                        InputHelper.readString("Crop ID: ");

                    String date =
                        InputHelper.readString("Date: ");

                    String description =
                        InputHelper.readString("Description: ");

                    double quantity =
                        InputHelper.readDouble("Quantity: ");

                    String unit =
                        InputHelper.readString("Unit (kg/sacks): ");

                    String quality =
                        InputHelper.readString(
                            "Quality (Premium/Standard/Low Grade): ");

                    HarvestRecord harvest = new HarvestRecord(
                        recordID, cropID, date,
                        description, quantity, unit, quality
                    );

                    harvestManager.addHarvest(harvest);
                    break;

                case 2:
                    harvestManager.viewAllHarvests();
                    System.out.println("\nPress Enter to continue...");
                    InputHelper.readString("");
                    break;

                case 3:
                    String harvestID =
                        InputHelper.readString("Record ID: ");

                    harvestManager.searchHarvest(harvestID);
                    break;

                case 4:
                    String harvestCropID =
                        InputHelper.readString("Crop ID: ");

                    System.out.println(
                        harvestManager.calculateTotalHarvest(
                            harvestCropID));
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------- Sales Menu ----------

    private static void salesMenu() {

        System.out.println("\n========== SALES MANAGEMENT ==========");
        System.out.println("1. Add Sale");
        System.out.println("2. View All Sales");
        System.out.println("3. Search Sale");
        System.out.println("4. Calculate Total Revenue");
        System.out.println("5. Calculate Revenue By Crop");
        System.out.println("0. Back");

        int choice = InputHelper.readInt("Enter choice: ");

        try {
            switch (choice) {

                case 1:
                    String recordID =
                        InputHelper.readString("Record ID: ");

                    String cropID =
                        InputHelper.readString("Crop ID: ");

                    String date =
                        InputHelper.readString("Date: ");

                    String description =
                        InputHelper.readString("Description: ");

                    double quantitySold =
                        InputHelper.readDouble("Quantity Sold: ");

                    String unit =
                        InputHelper.readString("Unit: ");

                    double pricePerUnit =
                        InputHelper.readDouble("Price Per Unit: ");

                    SalesRecord sale = new SalesRecord(
                        recordID, cropID, date,
                        description, quantitySold,
                        unit, pricePerUnit
                    );

                    salesManager.addSale(sale);
                    break;

                case 2:
                    salesManager.viewAllSales();
                    System.out.println("\nPress Enter to continue...");
                    InputHelper.readString("");
                    break;

                case 3:
                    String saleID =
                        InputHelper.readString("Record ID: ");

                    SalesRecord foundSale =
                        salesManager.searchSale(saleID);

                    if (foundSale != null) {
                        foundSale.displayDetails();
                    } else {
                        System.out.println("Sale record not found.");
                    }
                    System.out.println("\nPress Enter to continue...");
                    InputHelper.readString("");
                    break;

                case 4:
                    System.out.printf(
                        "Total Revenue: PHP %,.2f%n",
                        salesManager.calculateTotalRevenue());
                    System.out.println("\nPress Enter to continue...");
                    InputHelper.readString("");
                    break;

                case 5:
                    String salesCropID =
                        InputHelper.readString("Crop ID: ");

                    System.out.printf(
                        "Revenue: PHP %,.2f%n",
                        salesManager.calculateRevenueByCrop(
                            salesCropID));
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------- Financial Reports ----------

    private static void reportMenu() {

        System.out.println("\n========== FINANCIAL REPORTS ==========");
        System.out.println("1. Overall Farm Report");
        System.out.println("2. Crop Financial Report");
        System.out.println("0. Back");

        int choice = InputHelper.readInt("Enter choice: ");

        try {
            switch (choice) {

                case 1:
                    System.out.println(
                        financialReport.generateOverallReport());
                    System.out.println("\nPress Enter to continue...");
                    InputHelper.readString("");
                    break;

                case 2:
                    String cropID =
                        InputHelper.readString("Enter Crop ID: ");

                    System.out.println(
                        financialReport.generateCropReport(cropID));
                    System.out.println("\nPress Enter to continue...");
                    InputHelper.readString("");
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
