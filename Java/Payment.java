
package Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.Scanner;

public class Payment {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n--- PAYMENT MANAGEMENT ---");
            System.out.println("1. Add Payment");
            System.out.println("2. View Payments");
            System.out.println("3. Update Payment");
            System.out.println("4. Delete Payment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addPayment();
                    break;

                case 2:
                    viewPayments();
                    break;

                case 3:
                    updatePayment();
                    break;

                case 4:
                    deletePayment();
                    break;

                case 5:
                    System.out.println("Exiting Payment Management...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);
    }

    // Add Payment
    static void addPayment() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Bill ID: ");
            int billId = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Payment Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            System.out.print("Enter Amount Paid: ");
            double amount = sc.nextDouble();

            sc.nextLine();

            System.out.print("Enter Payment Method: ");
            String method = sc.nextLine();

            String sql = "INSERT INTO payment "
                    + "(bill_id, payment_date, amount_paid, payment_method) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, billId);
            ps.setDate(2, Date.valueOf(date));
            ps.setDouble(3, amount);
            ps.setString(4, method);

            ps.executeUpdate();

            System.out.println("Payment Added Successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Payments
    static void viewPayments() {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM payment";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- PAYMENT DETAILS ---");

            while (rs.next()) {

                System.out.println(
                    rs.getInt("payment_id") + " | "
                    + rs.getInt("bill_id") + " | "
                    + rs.getDate("payment_date") + " | "
                    + rs.getDouble("amount_paid") + " | "
                    + rs.getString("payment_method")
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// Update Payment
static void updatePayment() {

    try {
        Connection con = DBConnection.getConnection();

        System.out.print("Enter Payment ID to Update: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Amount Paid: ");
        double amount = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter New Payment Method: ");
        String method = sc.nextLine();

        String sql = "UPDATE payment SET amount_paid=?, "
                + "payment_method=? WHERE payment_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setDouble(1, amount);
        ps.setString(2, method);
        ps.setInt(3, id);

        int result = ps.executeUpdate();

        if (result > 0) {
            System.out.println("Payment Updated Successfully!");
        } else {
            System.out.println("Payment ID Not Found!");
        }

        ps.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // Delete Payment
    static void deletePayment() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Payment ID to Delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM payment WHERE payment_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Payment Deleted Successfully!");
            } else {
                System.out.println("Payment ID Not Found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
