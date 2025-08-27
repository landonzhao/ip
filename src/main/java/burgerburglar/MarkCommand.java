package burgerburglar;

public class MarkCommand extends Command {
    private final String args;
    private final boolean isMark;

    public MarkCommand(String args, boolean isMark) {
        this.args = args;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            Task updated = tasks.markTask(index, isMark);

            storage.save(tasks);

        } catch (NumberFormatException e) {
            throw new BurgerException("BURGER ERROR: THAT'S NOT A NUMBER.");
        } catch (IndexOutOfBoundsException e) {
            throw new BurgerException("BURGER ERROR: INVALID TASK NUMBER.");
        }
    }
}