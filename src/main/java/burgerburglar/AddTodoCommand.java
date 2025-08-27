package burgerburglar;

public class AddTodoCommand extends Command {
    private final String args;

    public AddTodoCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        if (args == null || args.trim().isEmpty()) {
            throw new BurgerException("BURGER ERROR: The description of a todo cannot be empty.");
        }
        Task task = new Todo(args.trim());
        tasks.addTask(task);
        storage.save(tasks);
        ui.showMessage("BURGER ADDED: " + task + "\nNOW YOU HAVE " + tasks.size() + " TASK(S).");
    }
}
