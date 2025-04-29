import java.util.ArrayList;
import java.util.Scanner;

public class ToDoApp {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Show current tasks
    private static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show!");
        } else {
            System.out.println("\n===== Your Tasks =====");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    // Add a new task
    private static void addTask() {
        System.out.print("Enter the task: ");
        String task = scanner.nextLine();
        tasks.add(task);
    }

    // Delete a task
    private static void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to delete!");
            return;
        }
        showTasks();
        System.out.print("Enter task number to delete: ");
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(scanner.nextLine());
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("Invalid task number!");
            } else {
                tasks.remove(taskNumber - 1);
                System.out.println("Task removed successfully!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== To-Do List =====");
            System.out.println("1. Show Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Quit");

            System.out.print("Choose an option (1/2/3/4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showTasks();
                    break;
                case "2":
                    addTask();
                    break;
                case "3":
                    deleteTask();
                    break;
                case "4":
                    System.out.println("Exiting To-Do app...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
