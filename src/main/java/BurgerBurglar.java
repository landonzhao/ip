import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BurgerBurglar {

    private static final String VERSION = "v0.7";
    private static final String LOGO =
                    "                           ................\n" +
                    "                    ...:::::--:::::::::::::::::...\n" +
                    "                ...::----::------::.:--------::--::...\n" +
                    "              ..:--------:-------------------::------:..\n" +
                    "            ..:---------------:::----------------------:..\n" +
                    "           ..------:::-----------------::--------:.:--===:..\n" +
                    "           .-----------------------------------------=====:.\n" +
                    "         ..----------------------------------------========..\n" +
                    "         .:-------------------------------------===========-.\n" +
                    "         .--------------------------------==================.\n" +
                    "         .======--:---.:-:-----====-===::=:=====+-:=-=-=====.\n" +
                    "         ..=++++=-:=:-=--:::-====--:-:--:=:-:--:-==--=-+++=..\n" +
                    "           ..:*##########%%%%%%%%%%%@@@@@%%%%%%%%%%%%%%*:..\n" +
                    "         ..:------------------===-:-=::-==================:....\n" +
                    "         .-#%@@@@@@@%#+-----::::::::::::---====+#%@@@@@@@%#-.\n" +
                    "         .*#########%%@@@@@@%#=---:::--=#%@@@@@@%%####%%%##*.\n" +
                    "         .-#%%#################%%%%%%%%####%%%#############-.\n" +
                    "       ....=******###################################*****=...\n" +
                    "       ..-==================================================-..\n" +
                    "       .....==============================================.....\n" +
                    "           .:-----------===========--------==============-..\n" +
                    "           ..:--=================================+++++==:...\n" +
                    "            ......:::::::::::::::::::::::::::::::::::....\n" +
                    "\n" +
                    "----------------------------------------------------------------------\n";

    private static final String LINE_BREAK =
            "______________________________________________________________________\n";

    private static TaskList list = Storage.load();

    public static void main(String[] args) {
        greet();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("bye")) {
                    exit();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    showList();
                } else if (input.startsWith("mark")) {
                    mark(input, true);
                    Storage.save(list);
                } else if (input.startsWith("unmark")) {
                    mark(input, false);
                    Storage.save(list);
                } else if (input.startsWith("delete")) {
                    handleDelete(input);
                } else if (input.startsWith("todo")) {
                    addTodo(input.substring(5).trim());
                    Storage.save(list);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input.substring(9).trim());
                    Storage.save(list);
                } else if (input.startsWith("event")) {
                    handleEvent(input.substring(6).trim());
                    Storage.save(list);
                } else if (input.equalsIgnoreCase("burger")){
                    System.out.print(LINE_BREAK);
                    System.out.println("BURGER IS BURGER, AND YOU ARE THE FRIES.");
                    System.out.print(LINE_BREAK);
                } else {
                    System.out.println("BURGER DOESN’T GET IT.");
                }
            }
        }
    }

    private static void greet() {
        System.out.println(LOGO + "INITIATING BURGERBURGLAR " + VERSION + "\n" +
                "------------------------------------------------------------------100%\n");
        printUserManual();
        System.out.println("BURGER SAYS HI.\nWHAT CAN BURGER STEAL FOR YOU?");
        System.out.println(LINE_BREAK);
    }

    private static void exit() {
        System.out.print(LINE_BREAK);
        System.out.println("GOODBYE, GOODBURGER.");
        System.out.println(LINE_BREAK);
        System.out.println("EXITING BURGERBURGLAR " + VERSION);
    }

    private static void addTodo(String description) {
        if (description.isEmpty()) {
            System.out.println("BURGER ERROR: TODO NEEDS A DESCRIPTION!");
            return;
        }
        list.addTask(new Todo(description));
    }

    private static void handleDeadline(String input) {
        String[] parts = input.split("/by", 2);
        String description = parts[0].trim();

        if (description.isEmpty()) {
            System.out.println("BURGER ERROR: DEADLINE NEEDS A DESCRIPTION!");
            return;
        }

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            System.out.println("BURGER ERROR: DEADLINE NEEDS A DATE/TIME!");
            return;
        }

        String dateStr = parts[1].trim();
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            LocalDateTime date = LocalDateTime.parse(dateStr, inputFormat);
            list.addTask(new Deadline(description, date));
        } catch (DateTimeParseException e) {
            System.out.println("BURGER ERROR: INVALID DATE FORMAT! Use yyyy-MM-dd HHmm");
        }
    }

    private static void handleEvent(String input) {
        String[] partsFrom = input.split("/from", 2);
        String description = partsFrom[0].trim();

        if (description.isEmpty()) {
            System.out.println("BURGER ERROR: EVENT NEEDS A DESCRIPTION!");
            return;
        }

        if (partsFrom.length < 2 || partsFrom[1].trim().isEmpty()) {
            System.out.println("BURGER ERROR: EVENT NEEDS A START DATE/TIME!");
            return;
        }

        String[] partsTo = partsFrom[1].split("/to", 2);
        String fromStr = partsTo[0].trim();
        String toStr = (partsTo.length > 1) ? partsTo[1].trim() : "";

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            LocalDateTime from = LocalDateTime.parse(fromStr, inputFormat);
            LocalDateTime to = toStr.isEmpty() ? null : LocalDateTime.parse(toStr, inputFormat);
            list.addTask(new Event(description, from, to));
        } catch (DateTimeParseException e) {
            System.out.println("BURGER ERROR: INVALID DATE FORMAT! Use yyyy-MM-dd HHmm for /from and /to");
        }
    }

    private static void handleDelete(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("BURGER ERROR: WHAT DO YOU WANT TO DELETE?");
            return;
        }

        try {
            int taskNumber = Integer.parseInt(parts[1].trim());
            Task removed = list.deleteTask(taskNumber - 1);
        } catch (NumberFormatException e) {
            System.out.println("BURGER ERROR: THAT’S NOT A NUMBER.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("BURGER ERROR: INVALID TASK NUMBER.");
        }
    }

    private static void mark(String input, boolean done) {
        try {
            String[] parts = input.split(" ");
            int taskNumber = Integer.parseInt(parts[1]) - 1; // 0-based index
            Task task = list.markTask(taskNumber, done);

            System.out.print(LINE_BREAK);
            if (done) {
                System.out.println("YOU DID IT! BURGER!\nMARKED:  " + task);
            } else {
                System.out.println("BURGER IS ASHAMED OF YOU.\nUNMARKED:  " + task);
            }
            System.out.println(LINE_BREAK);

        } catch (Exception e) {
            System.out.println("INVALID INPUT. Usage: mark <number> or unmark <number>");
        }
    }

    private static void showList() {
        System.out.println(list);
    }

    private static void printUserManual() {
        String manual = """
        BURGERBURGLAR v0.7 – USER MANUAL
        ______________________________________________________________________

        GOODDAY AND GOODNIGHT. THIS IS BURGER.
        BURGER STEALS YOUR TASKS AND MANAGES THEM.
        HERE'S HOW YOU CAN BURGER YOUR TASKS:

        COMMANDS:

        1. Add Tasks
           - todo <description>
             Example: todo read book
             Adds a simple task to your list.

           - deadline <description> /by <date>
             Example: deadline return book /by June 6th
             Adds a task with a deadline.

           - event <description> /from <start time> /to <end time>
             Example: event project meeting /from Aug 6th 2pm /to Aug 6th 4pm
             Adds an event with a start and end time.

        2. List Tasks
           - list
             Shows all tasks with their status and type.

        3. Mark Tasks as Done or Not Done
           - mark <task number>
             Example: mark 2
             Marks the task as done.

           - unmark <task number>
             Example: unmark 2
             Marks the task as not done.

        4. Delete Tasks
           - delete <task number>
             Example: delete 3
             Removes the task from your list.

        5. Exit BURGER
           - bye
             Saves your tasks and exits the program.
       
        BONUS: Say BURGER for surprise!

        ______________________________________________________________________
        NOTES:
        - Tasks are automatically saved to disk whenever you add, delete, or update them.
        - If you run BURGER for the first time, it will create a storage file automatically.
        - BURGER ignores corrupted lines in the storage file, so your other tasks remain safe.
        ______________________________________________________________________
        """;

        System.out.println(manual);
    }
}
