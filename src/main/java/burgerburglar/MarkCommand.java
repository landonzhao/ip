package burgerburglar;

/**
 * Represents a command to mark or unmark a task as done in the task list.
 * This command updates the task status and saves the task list to storage.
 */
public class MarkCommand extends Command {
    private final String args;
    private final boolean isMark;

    /**
     * Constructs a MarkCommand with the given task index and mark/unmark flag.
     *
     * @param args   the string containing the index of the task to mark/unmark
     * @param isMark true to mark the task as done, false to unmark it
     */
    public MarkCommand(String args, boolean isMark) {
        this.args = args;
        this.isMark = isMark;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            Task updated = tasks.markTask(index, isMark);

            storage.save(tasks);

            if (isMark) {
                return "YOU DID IT! BURGER!\nMARKED: " + updated;
            } else {
                return "BURGER IS ASHAMED OF YOU.\nUNMARKED: " + updated;
            }

        } catch (NumberFormatException e) {
            throw new BurgerException("BURGER ERROR: THAT'S NOT A NUMBER.");
        } catch (IndexOutOfBoundsException e) {
            throw new BurgerException("BURGER ERROR: INVALID TASK NUMBER.");
        }
    }
}
