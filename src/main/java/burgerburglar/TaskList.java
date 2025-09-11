package burgerburglar;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        assert tasks != null : "Task list should be initialized";
    }

    public TaskList(List<Task> tasks) {
        assert tasks != null : "Input task list cannot be null";
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the list and prints a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add null task to task list";
        tasks.add(task);
        assert tasks.contains(task) : "Task should have been added successfully";
    }

    /**
     * Marks a task as done or undone.
     *
     * @param index  Index of the task (0-based).
     * @param isDone True if the task is to be marked done, false if undone.
     * @return The updated task.
     */
    public Task markTask(int index, boolean isDone) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds for markTask";
        Task task = tasks.get(index);
        assert task != null : "Task at index should not be null";
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
        assert tasks.size() > 0 : "Cannot delete from an empty task list";
        if (index <= 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Task removed = tasks.remove(index - 1);
        assert removed != null : "Removed task should not be null";
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
        return sb.toString();
    }
}

