import java.sql.*;
import java.util.Scanner;

public class Student {
    private int id;
    private String name, email, password;

    public static void register(Scanner sc) {
    try (Connection conn = DBConnection.getConnection()) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        String sql = "INSERT INTO students (name, email, password) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.executeUpdate();

        System.out.println("Registration successful!");
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    public static int login(Scanner sc) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            String sql = "SELECT id FROM students WHERE email = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                return rs.getInt("id");
            } else {
                System.out.println("Invalid credentials.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
        
    }
}