import java.sql.*;
import java.util.Scanner;

public class Progress {
    public static void markTopicCompleted(int studentId) {
        try (Scanner sc = new Scanner(System.in);
             Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter topic ID to mark completed: ");
            int topicId = Integer.parseInt(sc.nextLine());
            String sql = "INSERT INTO progress (student_id, topic_id, completed) VALUES (?, ?, TRUE)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, topicId);
            ps.executeUpdate();
            System.out.println("Marked as completed.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void viewCompletedTopics(int studentId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT t.topic_name FROM topics t JOIN progress p ON t.id = p.topic_id WHERE p.student_id = ? AND p.completed = TRUE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            System.out.println("Completed Topics:");
            while (rs.next()) {
                System.out.println("- " + rs.getString("topic_name"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
