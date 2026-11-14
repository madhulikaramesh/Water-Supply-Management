
package Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MeterReading {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Database Connection Failed!");
                return;
            }

            System.out.println("\n--- METER READING MANAGEMENT ---");
            System.out.println("1. Add Meter Reading");
            System.out.println("2. View Meter Readings");
            System.out.println("3. Update Meter Reading");
            System.out.println("4. Delete Meter Reading");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {

                System.out.print("Enter Connection ID: ");
                int connectionId = sc.nextInt();

                System.out.print("Enter Reading Date (YYYY-MM-DD): ");
                String date = sc.next();

                System.out.print("Enter Previous Reading: ");
                double previous = sc.nextDouble();

                System.out.print("Enter Current Reading: ");
                double current = sc.nextDouble();

                String sql = "INSERT INTO meter_reading " +
                    "(connection_id, reading_date, previous_reading, current_reading) " +
                    "VALUES (?, ?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, connectionId);
                ps.setString(2, date);
                ps.setDouble(3, previous);
                ps.setDouble(4, current);

                ps.executeUpdate();

                System.out.println("Meter Reading Added Successfully!");

            } else if (choice == 2) {

                String sql = "SELECT * FROM meter_reading";

                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(
                        rs.getInt("reading_id") + " | " +
                        rs.getInt("connection_id") + " | " +
                        rs.getDate("reading_date") + " | " +
                        rs.getDouble("previous_reading") + " | " +
                        rs.getDouble("current_reading")
                    );
                }

            } else if (choice == 3) {

                System.out.print("Enter Reading ID to Update: ");
                int id = sc.nextInt();

                System.out.print("Enter New Previous Reading: ");
                double previous = sc.nextDouble();

                System.out.print("Enter New Current Reading: ");
                double current = sc.nextDouble();

                String sql = "UPDATE meter_reading SET " +
                    "previous_reading = ?, current_reading = ? " +
                    "WHERE reading_id = ?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setDouble(1, previous);
                ps.setDouble(2, current);
                ps.setInt(3, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Meter Reading Updated Successfully!");
                } else {
                    System.out.println("Reading ID Not Found!");
                }

            } else if (choice == 4) {

                System.out.print("Enter Reading ID to Delete: ");
                int id = sc.nextInt();

                String checkSql =
                    "SELECT * FROM water_bill WHERE reading_id = ?";

                PreparedStatement checkPs = con.prepareStatement(checkSql);
                checkPs.setInt(1, id);

                ResultSet rs = checkPs.executeQuery();

                if (rs.next()) {

                    System.out.println("Cannot Delete!");
                    System.out.println(
                        "This Reading ID is linked with a Water Bill."
                    );

                } else {

                    String sql =
                        "DELETE FROM meter_reading WHERE reading_id = ?";

                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, id);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        System.out.println(
                            "Meter Reading Deleted Successfully!"
                        );
                    } else {
                        System.out.println("Reading ID Not Found!");
                    }
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
