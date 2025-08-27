package burgerburglar;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye("v0.7");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}