import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class VulnerableApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        try {
            // Hardcoded credentials (Bad Practice)
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb", "root", "root123");

            Statement stmt = conn.createStatement();

            // SQL Injection vulnerability
            String query = "SELECT * FROM users WHERE username = '" 
                           + username + "' AND password = '" + password + "'";
            
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Login Successful!");
            } else {
                System.out.println("Login Failed!");
            }

            // Resource leak: connection & statement not closed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
