
package Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewWaterConnections {

    public static void main(String[] args) {

        String sql = "SELECT * FROM water_connection";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- WATER CONNECTION DETAILS ---");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                    rs.getInt("connection_id") + " | "
                    + rs.getInt("consumer_id") + " | "
                    + rs.getString("connection_type") + " | "
                    + rs.getString("status")
                );
            }

            if (!found) {
                System.out.println("No Water Connections Found!");
            }

        } catch (Exception e) {
            System.out.println("Unable to View Water Connections!");
            e.printStackTrace();
        }
    }
}
