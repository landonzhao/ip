import java.util.Scanner;

public class BurgerBurglar {
    private static TaskList list = new TaskList();
    private static String logo =
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

    private static String lineBreak = "______________________________________________________________________\n";

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                showList();
            } else if (input.startsWith("mark")) {
                mark(input, true);
            } else if (input.startsWith("unmark")) {
                mark(input, false);
            } else if (input.startsWith("delete")) {
                String[] parts = input.split(" ", 2);
                if (parts.length < 2) {
                    System.out.println("BURGER ERROR: WHAT DO YOU WANT TO DELETE?");
                } else {
                    try {
                        int taskNumber = Integer.parseInt(parts[1].trim());
                        Task removed = list.deleteTask(taskNumber - 1);
                    } catch (NumberFormatException e) {
                        System.out.println("BURGER ERROR: HEY. THAT'S NOT A NUMBER.");
                    }
                }
            } else if (input.startsWith("todo")) {
                addTodo(input.substring(5)); // skip "todo "
            } else if (input.startsWith("deadline")) {
                handleDeadline(input.substring(9)); // skip "deadline "
            } else if (input.startsWith("event")) {
                handleEvent(input.substring(6)); // skip "event "
            } else {
                System.out.println("BURGER DON'T GET IT.");
            }
        }

        scanner.close();
    }

    private static void greet() {
        System.out.println(logo + "INITIATING BURGERBURGLAR v0.0\n" +
                "------------------------------------------------------------------100%\n");
        System.out.println("BURGER SAYS HI.\nWHAT CAN BURGER STEAL FOR YOU?");
        System.out.println(lineBreak);
    }

    private static void exit() {
        System.out.print(lineBreak);
        System.out.println("GOODBYE, GOODBURGER.");
        System.out.println(lineBreak);
        System.out.println("EXITING BURGERBURGLAR v0.0");
    }

    private static void addTodo(String desc) {
        Task t = new Todo(desc);
        list.addTask(t);
    }

    private static void handleDeadline(String input) {
        String[] parts = input.split("/by", 2);
        Task t = new Deadline(parts[0].trim(), parts.length > 1 ? parts[1].trim() : "unspecified");
        list.addTask(t);
    }

    private static void handleEvent(String input) {
        String[] partsFrom = input.split("/from", 2);
        String description = partsFrom[0].trim();
        String from = "", to = "";
        if (partsFrom.length > 1) {
            String[] partsTo = partsFrom[1].split("/to", 2);
            from = partsTo[0].trim();
            if (partsTo.length > 1) {
                to = partsTo[1].trim();
            }
        }
        Task t = new Event(description, from, to);
        list.addTask(t);
    }

    private static void mark(String input, boolean done) {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1; // convert to 0-based
            Task task = list.markTask(index, done);
            System.out.print(lineBreak);
            if (done) {
                System.out.println("YOU DID IT! BURGER!\nMARKED:  " + task);
            } else {
                System.out.println("BURGER IS ASHAMED OF YOU.\nUNMARKED:  " + task);
            }
            System.out.println(lineBreak);
        } catch (Exception e) {
            System.out.println("INVALID INPUT. Usage: mark <number> or unmark <number>");
        }
    }

    private static void showList() {
        System.out.println(list);
    }
}
