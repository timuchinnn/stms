package service;

import db.DatabaseManager;
import model.Task;
import model.TaskStatus;

import java.sql.*;
import java.util.ArrayList;
        import java.util.List;

public class TaskManager {
    // Добавление новой задачи (MVP Feature 2)
    public void addTask(String title, String description, int studentId) {
        String sql = "INSERT INTO tasks(title, description, status, student_id) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, TaskStatus.TODO.name());
            pstmt.setInt(4, studentId);
            pstmt.executeUpdate();
            System.out.println("Задача добавлена!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // Получение всех задач (MVP Feature 3)
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        TaskStatus.valueOf(rs.getString("status"))
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    // Обновление статуса задачи (MVP Feature 4)
    public void updateTaskStatus(int taskId, TaskStatus newStatus) {
        String sql = "UPDATE tasks SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus.name());
            pstmt.setInt(2, taskId);
            pstmt.executeUpdate();
            System.out.println("Статус задачи обновлен!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
