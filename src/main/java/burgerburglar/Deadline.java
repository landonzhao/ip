package burgerburglar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in BurgerBurglar.
 * Extends the {@link Task} class and includes a due date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description the description of the task
     * @param by          the deadline as a LocalDateTime
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the given description, deadline, and completion status.
     *
     * @param description the description of the task
     * @param by          the deadline as a LocalDateTime
     * @param isDone      whether the task is completed
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        String byDisplay = (by != null) ? by.format(OUTPUT_FORMAT) : "unspecified";
        return getTypeIcon() + getStatusIcon() + " " + description + " (by: " + byDisplay + ")";
    }

    @Override
    public String serialize() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + (by != null ? by.toString() : "");
    }
}
