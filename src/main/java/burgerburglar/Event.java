package burgerburglar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a start and end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event with description, start time, and end time.
     *
     * @param description the description of the event
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event with description, start time, end time, and done status.
     *
     * @param description the description of the event
     * @param from        the start time of the event
     * @param to          the end time of the event
     * @param isDone      the completion status of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        this(description, from, to);
        this.isDone = isDone;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        String fromDisplay = (from != null) ? from.format(OUTPUT_FORMAT) : "unspecified";
        String toDisplay = (to != null) ? to.format(OUTPUT_FORMAT) : "unspecified";
        return getTypeIcon() + getStatusIcon()
                + " " + description + " (from: " + fromDisplay + " to: " + toDisplay + ")";
    }

    @Override
    public String serialize() {
        return "E | "
                + (isDone ? "1" : "0") + " | "
                + description + " | "
                + (from != null ? from.toString() : "")
                + " | " + (to != null ? to.toString() : "");
    }
}
