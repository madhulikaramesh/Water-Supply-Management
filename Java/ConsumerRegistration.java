package Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ConsumerRegistration {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== CONSUMER REGISTRATION =====");

        System.out.print("Enter Consumer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        String sql = "INSERT INTO consumer "
                   + "(consumer_name, address, phone) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, phone);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Consumer Registered Successfully!");
            }

        } catch (Exception e) {
            System.out.println("Registration Failed!");
            e.printStackTrace();
        }

       sc.close();
    }
}