package burgerburglar;

/**
 * Represents the "BURGER" bonus command.
 * When executed, it prints the special "BURGER" message.
 */
public class BurgerCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBurger();
    }
}
