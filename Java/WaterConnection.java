package Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class WaterConnection {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== WATER CONNECTION MANAGEMENT =====");

        System.out.print("Enter Consumer ID: ");
        int consumerId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Connection Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Connection Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        System.out.print("Enter Status: ");
        String status = sc.nextLine();

        String sql = "INSERT INTO water_connection "
                   + "(consumer_id, connection_type, connection_date, status) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, consumerId);
            ps.setString(2, type);
            ps.setDate(3, java.sql.Date.valueOf(date));
            ps.setString(4, status);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Water Connection Added Successfully!");
            }

        } catch (Exception e) {
            System.out.println("Water Connection Failed!");
            e.printStackTrace();
        }

        sc.close();
    }
}