package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

enum Priority {Low, Medium, High}

public class Task {
    private String description;
    private Date dueDate;
    private boolean completed;
    private SimpleDateFormat parser = new SimpleDateFormat("MMM dd, yyyy");


    private Priority priority;

    public Task(String description, Date dueDate, String priority, boolean completed) {

        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
        this.priority = Priority.valueOf(priority.substring(0, 1).toUpperCase() + priority.substring(1));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return parser.format(dueDate);
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = Priority.valueOf(priority.substring(0, 1).toUpperCase() + priority.substring(1));;
    }
}
