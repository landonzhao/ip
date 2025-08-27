package burgerburglar;

/**
 * Represents a Todo task in BurgerBurglar.
 * <p>
 * A Todo is a simple task that has a description and a completion status,
 * but no associated date or time.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String serialize() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
