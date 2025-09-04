package burgerburglar;

/**
 * Represents a command that lists all tasks in the task list.
 * When executed, it prints the current tasks via the UI.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.toString();
    }
}
