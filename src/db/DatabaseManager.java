package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // Подключение к локальному файлу stms.db [cite: 35, 102]
    private static final String URL = "jdbc:sqlite:stms.db";

    public static Connection connect() throws SQLException {
        try {
            // Эта строка принудительно загружает драйвер
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не найден! Проверьте библиотеку.");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {
        String createStudentsTable = "CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL)";

        String createTasksTable = "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "description TEXT, " +
                "status TEXT DEFAULT 'TODO', " +
                "student_id INTEGER, " +
                "FOREIGN KEY(student_id) REFERENCES students(id))";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createStudentsTable);
            stmt.execute(createTasksTable);
            System.out.println("База данных инициализирована успешно."); // [cite: 78]
        } catch (SQLException e) {
            System.out.println("Ошибка БД: " + e.getMessage());
        }
    }
}