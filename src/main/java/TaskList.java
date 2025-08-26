import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {
    private static final String LINE_BREAK =
            "______________________________________________________________________\n";
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list and prints a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.print(LINE_BREAK);
        System.out.println("YOU BETTER FINISH THAT SOON. BURGERS.");
        System.out.println("ADDED: " + task);
        System.out.println("NOW YOU HAVE " + tasks.size() + " TASK(S).");
        System.out.println(LINE_BREAK);
    }

    /**
     * Marks a task as done or undone.
     *
     * @param index  Index of the task (0-based).
     * @param isDone True if the task is to be marked done, false if undone.
     * @return The updated task.
     */
    public Task markTask(int index, boolean isDone) {
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        return task;
    }

    /**
     * Deletes a task from the list and prints a confirmation message.
     *
     * @param index Index of the task (0-based).
     * @return The removed task.
     */
    public Task deleteTask(int index) {
        Task removed = tasks.remove(index);
        System.out.print(LINE_BREAK);
        System.out.println("BURGER HAS REMOVED THIS TASK:");
        System.out.println("  " + removed);
        System.out.println("NOW YOU HAVE " + tasks.size() + " TASK(S) IN THE LIST.");
        System.out.println(LINE_BREAK);
        return removed;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /** Returns a copy of the internal task list. */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return The task list as a formatted string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE_BREAK);
        if (tasks.isEmpty()) {
            sb.append("ALL DONE. BURGER!\n");
        } else {
            sb.append("YOU SHOULD GET STARTED SOON:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1)
                        .append(". ")
                        .append(tasks.get(i))
                        .append("\n");
            }
        }
        sb.append(LINE_BREAK);
        return sb.toString();
    }
}

