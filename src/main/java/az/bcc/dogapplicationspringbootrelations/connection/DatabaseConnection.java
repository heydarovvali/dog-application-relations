package az.bcc.dogapplicationspringbootrelations.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private static String url = "jdbc:postgresql://localhost:9696/test10";
    private static String username = "postgres";
    private static String password = "1996.Vali";

    private DatabaseConnection() {

    }

    public static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection succesfly");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
