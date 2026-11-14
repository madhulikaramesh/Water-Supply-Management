package Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewConsumers {

    public static void main(String[] args) {

        String sql = "SELECT * FROM consumer";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== CONSUMER DETAILS =====");

            System.out.printf("%-12s %-20s %-25s %-15s%n",
                    "ID", "NAME", "ADDRESS", "PHONE");

            System.out.println(
                    "-----------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-12d %-20s %-25s %-15s%n",
                        rs.getInt("consumer_id"),
                        rs.getString("consumer_name"),
                        rs.getString("address"),
                        rs.getString("phone"));
            }

        } catch (Exception e) {
            System.out.println("Unable to Display Consumers!");
            e.printStackTrace();
        }
    }
}