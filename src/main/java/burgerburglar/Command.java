package burgerburglar;

/**
 * Represents a general command in BurgerBurglar.
 * Each concrete command should implement the execute method.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException;
    public boolean isExit() {
        return false;
    }
}
