import java.sql.*;
import java.util.Scanner;

public class Subject {
    public static void listSubjects(Scanner sc) {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM subjects");
            System.out.println("Subjects:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
