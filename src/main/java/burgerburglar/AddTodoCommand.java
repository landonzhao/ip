package burgerburglar;

/**
 * Represents the command to add a Todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String args;

    public AddTodoCommand(String args) {
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        if (args == null || args.trim().isEmpty()) {
            throw new BurgerException("BURGER ERROR: The description of a todo cannot be empty.");
        }
        Task task = new Todo(args.trim());
        tasks.addTask(task);
        storage.save(tasks);
        return "BURGER ADDED: " + task + "\nNOW YOU HAVE " + tasks.size() + " TASK(S).";
    }
}
