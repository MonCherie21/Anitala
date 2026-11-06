
import java.util.Scanner;

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    // ---------- String Input ----------

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // ---------- Integer Input ----------

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input. Please enter a whole number.");
            }
        }
    }

    // ---------- Double Input ----------

    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            try {
                double value = Double.parseDouble(input);

                if (!Double.isFinite(value)) {
                    System.out.println(
                        "Please enter a valid finite number.");
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input. Please enter a numeric value.");
            }
        }
    }
}
