package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbCon {

    private static final String URL = 
        "jdbc:sqlserver://PC-CHAN\\SQLEXPRESS:1433;databaseName=CollabReservationDB;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("✅ Connection successful (Windows Authentication)");
        } else {
            System.out.println("❌ Connection failed");
        }
    }
}