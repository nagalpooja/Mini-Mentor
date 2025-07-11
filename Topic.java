import java.sql.*;
import java.util.Scanner;

public class Topic {
    public static void listTopicsBySubject(Scanner sc) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter subject ID: ");
            int subjectId = Integer.parseInt(sc.nextLine());
            String sql = "SELECT * FROM topics WHERE subject_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, subjectId);
            ResultSet rs = ps.executeQuery();
            System.out.println("Topics:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("topic_name"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
