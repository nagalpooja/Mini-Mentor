import java.sql.*;
import java.util.Scanner;

public class Admin {
    public static void addSubject(Scanner sc) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter subject name: ");
            String name = sc.nextLine();
            String sql = "INSERT INTO subjects (name) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
            System.out.println("Subject added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addTopic(Scanner sc) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter subject ID: ");
            int subjectId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter topic name: ");
            String topicName = sc.nextLine();
            String sql = "INSERT INTO topics (subject_id, topic_name) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, subjectId);
            ps.setString(2, topicName);
            ps.executeUpdate();
            System.out.println("Topic added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
