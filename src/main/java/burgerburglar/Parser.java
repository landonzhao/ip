package burgerburglar;

public class Parser {
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
            case "find":
                return new FindCommand(args);
            default:
                throw new BurgerException("BURGER DOESN’T GET IT.");
        }
    }
}