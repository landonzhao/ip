package burgerburglar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command to add an event task to the task list.
 * The command expects input in the format: (description) /from (start time) /to (end time).
 */
public class AddEventCommand extends Command {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String args;

    public AddEventCommand(String args) {
        assert args != null && !args.isBlank() : "Arguments for AddEventCommand cannot be null or blank";
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        assert tasks != null : "TaskList cannot be null in execute";
        assert ui != null : "Ui cannot be null in execute";
        assert storage != null : "Storage cannot be null in execute";
        assert args != null && !args.isBlank() : "Arguments cannot be null or blank in execute";

        String[] partsFrom = args.split("/from", 2);
        if (partsFrom.length < 2 || partsFrom[0].trim().isEmpty()) {
            throw new BurgerException("BURGER ERROR: Event must have a description and /from time.");
        }
        String description = partsFrom[0].trim();
        assert !description.isBlank() : "Event description cannot be blank";

        String[] partsTo = partsFrom[1].split("/to", 2);
        String fromStr = partsTo[0].trim();
        String toStr = (partsTo.length > 1) ? partsTo[1].trim() : "";

        try {
            LocalDateTime from = LocalDateTime.parse(fromStr, INPUT_FORMAT);

            LocalDateTime to = toStr.isEmpty() ? null : LocalDateTime.parse(toStr, INPUT_FORMAT);
            Event event = new Event(description, from, to);

            tasks.addTask(event);
            assert tasks.getTasks().contains(event) : "Event should be added to TaskList";

            storage.save(tasks);
            return "BURGER ADDED: " + event + "\nNOW YOU HAVE " + tasks.size() + " TASK(S).";
        } catch (DateTimeParseException e) {
            throw new BurgerException("BURGER ERROR: Invalid date/time format! Use yyyy-MM-dd HHmm");
        }
    }
}

