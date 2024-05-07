import java.io.Console;
import java.io.IOException;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        TaskList taskList1 = new TaskList("My Tasks");
        try {
            taskList1.addTask(
                    new Task("Buy groceries", new Date(), Priority.HIGH, new Category("Errands")));
            taskList1.addTask(
                    new Task("Finish homework", new Date(), Priority.MEDIUM, new Category("School")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int inputValue = 0;

        while (inputValue != -1) {
            Console console = System.console();

            String stringValue = console.readLine(
                    "Please enter a value: \n 1- See all the Tasks. \n 2- Add a new Task. \n(-1)- To exit \n");

            inputValue = Integer.parseInt(stringValue);

            if (!(inputValue >= -1 && inputValue <= 2)) {
                System.out.println("Please enter a valid number");
                break;
            }

            switch (inputValue) {
                // Show all tasks
                case 1:
                    System.out.println("Tasks: \n" + taskList1.stringifyAllTasks());
                    break;
                // Add a new task
                case 2:
                    String description = console.readLine("Please enter the description of the new Task: ");
                    Date dueDate = new Date(); // Assuming default due date for now
                    Priority priority = Priority.LOW; // Assuming default priority for now
                    Category category = new Category("Uncategorized"); // Assuming default category for now

                    // You can prompt the user for due date, priority, and category if needed
                    try {
                        taskList1.addTask(new Task(description, dueDate, priority, category));
                        System.out.println("Task added successfully!");
                    } catch (IOException e) {
                        System.err.println("Error adding task: " + e.getMessage());
                    }
                    break;
                case -1:
                    System.out.println("Bye :)!!");
                    return;
                default:
                    break;
            }
        }
    }
}
