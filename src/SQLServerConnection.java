import java.sql.*;

public class SQLServerConnection {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Practice;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
        
        try (Connection conn = DriverManager.getConnection(connectionUrl)) {
            System.out.println("Connected to SQL Server!");

            // SQL query to get id, email, and password from the User table
            String sql = "SELECT id, email, password FROM [User]";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    String password = rs.getString("password");

                    System.out.println("ID: " + id + ", Email: " + email + ", Password: " + password);
                }

            } catch (SQLException queryEx) {
                System.err.println("Error executing query: " + queryEx.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
