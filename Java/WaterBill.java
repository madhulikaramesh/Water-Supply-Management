
package Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class WaterBill {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Database Connection Failed!");
                return;
            }

            System.out.println("\n--- WATER BILL MANAGEMENT ---");
            System.out.println("1. Add Water Bill");
            System.out.println("2. View Water Bills");
            System.out.println("3. Update Water Bill");
            System.out.println("4. Delete Water Bill");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                System.out.print("Enter Reading ID: ");
                int readingId = sc.nextInt();

                String readSql = "SELECT previous_reading, current_reading " +
                                 "FROM meter_reading WHERE reading_id = ?";

                PreparedStatement readPs = con.prepareStatement(readSql);
                readPs.setInt(1, readingId);

                ResultSet rs = readPs.executeQuery();

                if (rs.next()) {

                    double previous = rs.getDouble("previous_reading");
                    double current = rs.getDouble("current_reading");

                    double units = current - previous;
                    double amount = units * 5;

                    System.out.println("Units Consumed: " + units);
                    System.out.println("Bill Amount: Rs. " + amount);

                    System.out.print("Enter Due Date (YYYY-MM-DD): ");
                    String dueDate = sc.next();

                    String sql = "INSERT INTO water_bill " +
                        "(reading_id, units_consumed, bill_amount, due_date, payment_status) " +
                        "VALUES (?, ?, ?, ?, ?)";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setInt(1, readingId);
                    ps.setDouble(2, units);
                    ps.setDouble(3, amount);
                    ps.setString(4, dueDate);
                    ps.setString(5, "Unpaid");

                    ps.executeUpdate();

                    System.out.println("Water Bill Added Successfully!");

                } else {
                    System.out.println("Reading ID Not Found!");
                }

            } else if (choice == 2) {

                String sql = "SELECT * FROM water_bill";

                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(
                        rs.getInt("bill_id") + " | " +
                        rs.getInt("reading_id") + " | " +
                        rs.getDouble("units_consumed") + " | " +
                        rs.getDouble("bill_amount") + " | " +
                        rs.getDate("due_date") + " | " +
                        rs.getString("payment_status")
                    );
                }

            } else if (choice == 3) {

                System.out.print("Enter Bill ID to Update: ");
                int id = sc.nextInt();

                System.out.print("Enter New Bill Amount: ");
                double amount = sc.nextDouble();

                System.out.print("Enter New Due Date (YYYY-MM-DD): ");
                String date = sc.next();

                String sql = "UPDATE water_bill SET bill_amount = ?, " +
                             "due_date = ? WHERE bill_id = ?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setDouble(1, amount);
                ps.setString(2, date);
                ps.setInt(3, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Water Bill Updated Successfully!");
                } else {
                    System.out.println("Bill ID Not Found!");
                }

            } else if (choice == 4) {

                System.out.print("Enter Bill ID to Delete: ");
                int id = sc.nextInt();

                String sql = "DELETE FROM water_bill WHERE bill_id = ?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Water Bill Deleted Successfully!");
                } else {
                    System.out.println("Bill ID Not Found!");
                }

            } else {
                System.out.println("Invalid Choice!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        sc.close();
    }
}
