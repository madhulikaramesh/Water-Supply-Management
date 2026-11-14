
package Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteWaterConnection {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String sql = "DELETE FROM water_connection WHERE connection_id = ?";

        try {
            System.out.print("Enter Connection ID to Delete: ");
            int connectionId = sc.nextInt();

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, connectionId);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Water Connection Deleted Successfully!");
                } else {
                    System.out.println("Connection ID Not Found!");
                }
            }

        } catch (Exception e) {
            System.out.println("Unable to Delete Water Connection!");
            e.printStackTrace();
        }

        sc.close();
    }
}
