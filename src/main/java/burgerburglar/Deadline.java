package burgerburglar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    // Constructor takes LocalDateTime (already parsed in BurgerBurglar)
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

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
