import java.util.Date;
import java.text.SimpleDateFormat; // For date formatting

public class Task extends TaskItem implements Prioritizable {

    private String description;
    private Date dueDate;
    private Priority priority;
    private Category category; // Association with Category class

    public Task(String description, Date dueDate, Priority priority, Category category) {
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String getPriority() {
        return priority.toString(); // Assuming Priority is an enum
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String stringify() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss"); // Date format for filename

        String value = "Description: " + this.description + "\n" +
                "Due Date: " + formatter.format(this.dueDate) + "\n"
                + "Priority: " + this.priority + "\n" +
                "Category: " + this.category.getDescription() + "\n";

        return value;

    }
}
