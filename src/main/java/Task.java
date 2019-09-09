import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base Task class for holding variables and methods relevant to tasks.
 */
public class Task {
    /**
     * Description of task details.
     */
    protected String description;
    /**
     * Description of task type (eg. todo, deadline or event).
     */
    protected String taskType;
    /**
     * Boolean determining if task has been marked complete.
     */
    protected boolean isDone;
    /**
     * Date format for deadlines/event timings.
     */
    protected LocalDateTime dateTime;
    /**
     * Standard format of date presentation.
     */
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructor taking in the task details, type and whether it is complete.
     * @param description Description of task details
     * @param taskType Description of task type
     * @param isDone Boolean determining if task has been marked complete
     */
    public Task(String description, String taskType, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    /**
     * Second constructor, for deadlines/events which have a dateTime aspect.
     */
    public Task(String description, String taskType, LocalDateTime dateTime, boolean isDone) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = isDone;
        this.dateTime = dateTime;
    }

    /**
     * Returns tick or cross according to whether task is complete.
     * @return Tick or cross indicating completion of task
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    /**
     * Return task details.
     * @return Task details
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return task type.
     * @return Task type
     */
    public String getTaskType() {
        if (taskType.equals("todo")) {
            return "[T]";
        } else if (taskType.equals("deadline")) {
            return "[D]";
        } else if (taskType.equals("event")) {
            return "[E]";
        } else {
            return "";
        }
    }

    /**
     * Return dateTime string of relevant Task.
     * @return String parsed from dateTime aspect of task
     */
    public String getDateTime() {
        return dateTime.format(formatter);
    }

    /**
     * Indicates completion of chosen Task.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    
    @Override
    public String toString() {
        assert taskType.equals("deadline") || taskType.equals("event") || taskType.equals("todo")
                : "Task type is invalid.";
        String stringToPrint = getTaskType() + " " + getStatusIcon() + " " + getDescription();
        if (taskType.equals("deadline")) {
            return stringToPrint + " (by: " + getDateTime() + ")";
        } else if (taskType.equals("event")) {
            return stringToPrint + " (at: " + getDateTime() + ")";
        } else {
            return stringToPrint;
        }
    }
}
