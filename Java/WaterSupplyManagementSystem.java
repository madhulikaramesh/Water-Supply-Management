
package Java;

import java.util.Scanner;

public class WaterSupplyManagementSystem {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice=0;

        do {
            System.out.println("\n====================================");
            System.out.println("   WATER SUPPLY MANAGEMENT SYSTEM");
            System.out.println("====================================");

            System.out.println("1. Consumer Registration");
            System.out.println("2. View Consumers");
            System.out.println("3. Water Connection Management");
            System.out.println("4. View Water Connections");
            System.out.println("5. Update Water Connection");
            System.out.println("6. Delete Water Connection");
            System.out.println("7. Meter Reading");
            System.out.println("8. Water Bill");
            System.out.println("9. Payment Management");
            System.out.println("10. Exit");

            System.out.print("\nEnter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Choice!");
                continue;
            }

            switch (choice) {

                case 1:
                    launchModule("ConsumerRegistration");
                    break;

                case 2:
                    launchModule("ViewConsumers");
                    break;

                case 3:
                    launchModule("WaterConnection");
                    break;

                case 4:
                    launchModule("ViewWaterConnections");
                    break;

                case 5:
                    launchModule("UpdateWaterConnection");
                    break;

                case 6:
                    launchModule("DeleteWaterConnection");
                    break;

                case 7:
                    launchModule("MeterReading");
                    break;

                case 8:
                    launchModule("WaterBill");
                    break;

                case 9:
                    launchModule("Payment");
                    break;

                case 10:
                    System.out.println(
                        "Thank You! Application Closed."
                    );
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 10);
    }

    static void launchModule(String moduleName) {

        try {
            String javaPath = System.getProperty("java.home")
                    + "/bin/"
                    + (System.getProperty("os.name")
                    .toLowerCase().contains("win")
                    ? "java.exe" : "java");

            String className = "Java." + moduleName;

            ProcessBuilder pb = new ProcessBuilder(
                    javaPath,
                    "-cp",
                    System.getProperty("java.class.path"),
                    className
            );

            pb.inheritIO();

            Process process = pb.start();
            process.waitFor();

        } catch (Exception e) {
            System.out.println("Unable to Open Module!");
            e.printStackTrace();
        }
    }
}
