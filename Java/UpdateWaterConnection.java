
package Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateWaterConnection {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String sql = "UPDATE water_connection SET connection_type = ?, status = ? WHERE connection_id = ?";

        try {
            System.out.print("Enter Connection ID: ");
            int connectionId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Connection Type: ");
            String type = sc.nextLine();

            System.out.print("Enter New Status: ");
            String status = sc.nextLine();

            Connection con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Database Connection Failed!");
                return;
            }

            try (con;
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, type);
                ps.setString(2, status);
                ps.setInt(3, connectionId);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Water Connection Updated Successfully!");
                } else {
                    System.out.println("Connection ID Not Found!");
                }
            }

        } catch (Exception e) {
            System.out.println("Unable to Update Water Connection!");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
