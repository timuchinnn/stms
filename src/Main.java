import db.DatabaseManager;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Инициализация БД при старте [cite: 78]
        DatabaseManager.initializeDatabase();

        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        // Для простоты используем ID студента = 1 (как заглушку для MVP)
        int currentStudentId = 1;

        System.out.println("=== Student Task Management System ==="); // [cite: 9]

        while (true) {
            System.out.println("\nМеню:"); // [cite: 80]
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать все задачи");
            System.out.println("3. Обновить статус задачи");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введите название задачи: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите описание: ");
                    String desc = scanner.nextLine();
                    taskManager.addTask(title, desc, currentStudentId);
                    break;
                case "2":
                    System.out.println("\n--- Ваши задачи ---");
                    for (Task task : taskManager.getAllTasks()) {
                        System.out.println(task);
                    }
                    break;
                case "3":
                    System.out.print("Введите ID задачи: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Выберите статус (1-TODO, 2-IN_PROGRESS, 3-DONE): ");
                    int statusChoice = Integer.parseInt(scanner.nextLine());
                    TaskStatus status = switch (statusChoice) {
                        case 2 -> TaskStatus.IN_PROGRESS;
                        case 3 -> TaskStatus.DONE;
                        default -> TaskStatus.TODO;
                    };
                    taskManager.updateTaskStatus(id, status);
                    break;
                case "0":
                    System.out.println("Выход из приложения."); // [cite: 84]
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}