package burgerburglar;

/**
 * Represents the "BURGER" bonus command.
 * When executed, it prints the special "BURGER" message.
 */
public class BurgerCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "BURGER IS BURGER, AND YOU ARE THE FRIES.";
    }
}
