import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

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
        return getTypeIcon() + getStatusIcon() + " " + description + " (from: " + fromDisplay + " to: " + toDisplay + ")";
    }

    @Override
    public String serialize() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                (from != null ? from.toString() : "") + " | " + (to != null ? to.toString() : "");
    }
}
