package burgerburglar;

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
