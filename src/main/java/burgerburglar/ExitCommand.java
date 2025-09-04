package burgerburglar;

/**
 * Represents the exit command which terminates the BurgerBurglar program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "GOODBYE, GOODBURGER.";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
