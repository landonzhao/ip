package burgerburglar;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    private static final String LINE_BREAK =
            "______________________________________________________________________";

    // Show the welcome message with logo and version
    public void showWelcome(String logo, String version) {
        System.out.println(logo + "INITIATING BURGERBURGLAR " + version + "\n" +
                "------------------------------------------------------------------100%\n");
        showLine();
        printUserManual();
        showLine();
        System.out.println("GOOD DAY, GOOD BURGER.\nWHAT CAN BURGER STEAL FOR YOU?");
    }

    // Show goodbye message
    public void showGoodbye(String version) {
        showLine();
        System.out.println("GOODBYE, GOODBURGER.");
        showLine();
        System.out.println("EXITING BURGERBURGLAR " + version);
    }

    // Print a line break
    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    // Read input from the user
    public String readCommand() {
        return scanner.nextLine();
    }

    // Show an error message
    public void showError(String message) {
        showLine();
        System.out.println("BURGER ERROR: " + message);
        showLine();
    }

    // General message printing
    public void showMessage(String message) {
        System.out.println(message);
    }

    // Show the list of tasks (delegated to TaskList's toString)
    public void showTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    // Show the "BURGER" bonus message
    public void showBurger() {
        showLine();
        System.out.println("BURGER IS BURGER, AND YOU ARE THE FRIES.");
        showLine();
    }

    public void printUserManual() {
        String manual = """
        BURGERBURGLAR – USER MANUAL
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
             Example: deadline return book /by 2025-06-06 1800
             Adds a task with a deadline.

           - event <description> /from <start time> /to <end time>
             Example: event project meeting /from 2025-08-06 1400 /to 2025-08-06 1600
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
