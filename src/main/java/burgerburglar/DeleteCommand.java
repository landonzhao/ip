package burgerburglar;

/**
 * Represents a command to delete a task from the task list.
 * The task to be deleted is specified by its index in the list.
 */
public class DeleteCommand extends Command {
    private final String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            Task removed = tasks.deleteTask(index);
            storage.save(tasks);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new BurgerException("BURGER ERROR: Invalid task number for delete.");
        }
    }
}
