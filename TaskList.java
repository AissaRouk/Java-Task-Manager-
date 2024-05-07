import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat; // For date formatting
import java.util.ArrayList;
import java.util.List;

public class TaskList {

  private List<Task> tasks;
  private String name; // Added field to store TaskList name

  // Normal constructor (initialize name)
  public TaskList(String name) {
    this.tasks = new ArrayList<>();
    this.name = name;
  }

  // Constructor with List (initialize name to default)
  public TaskList(List<Task> taskList) {
    this.tasks = new ArrayList<>(taskList);
    this.name = "default_taskList"; // Set a default name
  }

  // Function to add a task and save it to a file
  public void addTask(Task task) throws IOException {
    tasks.add(task);
    String filename = getTaskFilename(task);

    // Check if file already exists and content is the same
    if (!fileExistsWithSameContent(filename, task)) {
      saveTaskToFile(task, filename); // Save only if content differs
    }
  }

  // Function to remove a task
  public void removeTask(Task task) {
    tasks.remove(task);
  }

  // Function to return the tasks list (unchmodifiable)
  public List<Task> getTasks() {
    return List.copyOf(tasks);
  }

  // Get name function
  public String getName() {
    return this.name;
  }

  // Set name function
  public void setName(String name) {
    this.name = name;
  }

  // Function to save the task list to a file (optional, not used here)
  public void saveTaskListToFile() throws IOException {
    // Implement logic to save the entire TaskList if needed
  }

  // Function to save a single task to a file (with filename)
  private void saveTaskToFile(Task task, String filename) throws IOException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss"); // Date format for filename

    FileWriter writer = new FileWriter(filename);

    try {
      writer.write("Description: " + task.getDescription() + "\n");
      writer.write("Due Date: " + formatter.format(task.getDueDate()) + "\n");
      writer.write("Priority: " + task.getPriority() + "\n");
      writer.write("Category: " + task.getCategory().getDescription() + "\n"); // Assuming Category has a getName()
                                                                               // method
    } finally {
      writer.close();
    }
  }

  // Function to get the filename for a task
  private String getTaskFilename(Task task) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss"); // Date format for filename
    return task.getDescription() + "_" + formatter.format(task.getDueDate()) + ".txt";
  }

  // Function to check if a file exists with the same content
  private boolean fileExistsWithSameContent(String filename, Task task) throws IOException {
    if (!new java.io.File(filename).exists()) {
      return false; // File doesn't exist, needs saving
    }

    BufferedReader reader = new BufferedReader(new FileReader(filename));
    try {
      String expectedContent = task.stringify();
      String line;
      StringBuilder actualContent = new StringBuilder();

      while ((line = reader.readLine()) != null) {
        actualContent.append(line).append("\n");
      }

      return expectedContent.equals(actualContent.toString()); // Compare expected vs actual content
    } finally {
      reader.close();
    }
  }

  // Functions that returns a string with all the Tasks
  public String stringifyAllTasks() {
    if (tasks.size() == 0)
      return "";

    String value = "";
    int i = 0;

    for (Task task : tasks) {
      value = value + " Task " + i + ": " + task.stringify();
      i++;
    }

    return value;
  }
}
