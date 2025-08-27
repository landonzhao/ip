package burgerburglar;

/**
 * Parses user input into a {@link Command} that can be executed by {@link BurgerBurglar}.
 * <p>
 * The Parser interprets the first word of the input as the command keyword and
 * the remaining text as arguments for that command.
 */
public class Parser {

    /**
     * Parses a string input from the user and returns the corresponding {@link Command}.
     *
     * @param input the raw input string from the user
     * @return a {@link Command} object representing the action requested by the user
     * @throws BurgerException if the input does not match any known command
     */
    public static Command parse(String input) throws BurgerException {
        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(args, true);
            case "unmark":
                return new MarkCommand(args, false);
            case "delete":
                return new DeleteCommand(args);
            case "todo":
                return new AddTodoCommand(args);
            case "deadline":
                return new AddDeadlineCommand(args);
            case "event":
                return new AddEventCommand(args);
            case "burger":
                return new BurgerCommand();
            default:
                throw new BurgerException("BURGER DOESN’T GET IT.");
        }
    }
}
