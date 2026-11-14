
package Java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
        "jdbc:mysql://localhost:3306/water_supply";

    private static final String USER = "root";

    private static final String PASSWORD =
        System.getenv("DB_PASSWORD");

    public static Connection getConnection() {

        try {
            if (PASSWORD == null || PASSWORD.isEmpty()) {
                throw new SQLException(
                    "DB_PASSWORD environment variable is not configured."
                );
            }

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD
            );

            System.out.println("Database Connected Successfully!");
            return con;

        } catch (Exception e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
}
