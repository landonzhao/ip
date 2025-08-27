package burgerburglar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String args;

    public AddEventCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        String[] partsFrom = args.split("/from", 2);
        if (partsFrom.length < 2 || partsFrom[0].trim().isEmpty()) {
            throw new BurgerException("BURGER ERROR: Event must have a description and /from time.");
        }
        String description = partsFrom[0].trim();

        String[] partsTo = partsFrom[1].split("/to", 2);
        String fromStr = partsTo[0].trim();
        String toStr = (partsTo.length > 1) ? partsTo[1].trim() : "";

        try {
            LocalDateTime from = LocalDateTime.parse(fromStr, INPUT_FORMAT);
            LocalDateTime to = toStr.isEmpty() ? null : LocalDateTime.parse(toStr, INPUT_FORMAT);
            Event event = new Event(description, from, to);
            tasks.addTask(event);
            storage.save(tasks);
            ui.showMessage("BURGER ADDED: " + event + "\nNOW YOU HAVE " + tasks.size() + " TASK(S).");
        } catch (DateTimeParseException e) {
            throw new BurgerException("BURGER ERROR: Invalid date/time format! Use yyyy-MM-dd HHmm");
        }
    }
}
