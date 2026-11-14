package Java;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            if (con != null) {
                System.out.println("Water Supply Database Ready!");
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}