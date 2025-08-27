import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private final String args;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public AddDeadlineCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        String[] parts = args.split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BurgerException("BURGER ERROR: Deadline must have a description and a /by date.");
        }

        String description = parts[0].trim();
        String byStr = parts[1].trim();

        try {
            LocalDateTime by = LocalDateTime.parse(byStr, INPUT_FORMAT);
            Deadline deadline = new Deadline(description, by);
            tasks.addTask(deadline);
            storage.save(tasks);
            ui.showMessage("BURGER ADDED: " + deadline + "\nNOW YOU HAVE " + tasks.size() + " TASK(S).");
        } catch (DateTimeParseException e) {
            throw new BurgerException("BURGER ERROR: Invalid date format! Use yyyy-MM-dd HHmm");
        }
    }
}