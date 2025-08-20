import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BurgerBurglar {
    private static List<Task> list = new ArrayList<>();
    private static String logo =
            "                           ................                           \n" +
                    "                    ...:::::--:::::::::::::::::...                    \n" +
                    "                ...::----::------::.:--------::--::...                \n" +
                    "              ..:--------:-------------------::------:..              \n" +
                    "            ..:---------------:::----------------------:..            \n" +
                    "           ..------:::-----------------::--------:.:--===:..          \n" +
                    "           .-----------------------------------------=====:.          \n" +
                    "         ..----------------------------------------========..         \n" +
                    "         .:-------------------------------------===========-.         \n" +
                    "         .--------------------------------==================.         \n" +
                    "         .======--:---.:-:-----====-===::=:=====+-:=-=-=====.         \n" +
                    "         ..=++++=-:=:-=--:::-====--:-:--:=:-:--:-==--=-+++=..         \n" +
                    "           ..:*##########%%%%%%%%%%%@@@@@%%%%%%%%%%%%%%*:..           \n" +
                    "         ..:------------------===-:-=::-==================:....       \n" +
                    "         .-#%@@@@@@@%#+-----::::::::::::---====+#%@@@@@@@%#-.         \n" +
                    "         .*#########%%@@@@@@%#=---:::--=#%@@@@@@%%####%%%##*.         \n" +
                    "         .-#%%#################%%%%%%%%####%%%#############-.         \n" +
                    "       ....=******###################################*****=...        \n" +
                    "       ..-==================================================-..       \n" +
                    "       .....==============================================.....       \n" +
                    "           .:-----------===========--------==============-..          \n" +
                    "           ..:--=================================+++++==:...          \n" +
                    "            ......:::::::::::::::::::::::::::::::::::....             \n" +
                    "                                                                      \n" +
                    "                                                                      \n" +
                    "           ...............................................            \n" +
                    "           ........::::::::::::::::::::::::::::::::........           \n" +
                    "                                                                      \n" +
                    "                                                                      \n" +
                    "                                                                      \n" +
                    "----------------------------------------------------------------------\n";

    private static String lineBreak = "______________________________________________________________________\n";
    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("YOU: ");
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
            }  else if (input.startsWith("todo")) {
                addTodo(input.substring(5)); // skip "todo "
            } else if (input.startsWith("deadline")) {
                handleDeadline(input.substring(9)); // skip "deadline "
            } else if (input.startsWith("event")) {
                handleEvent(input.substring(6)); // skip "event "
            } else {
                System.out.println("BURGER DOESN'T KNOW THAT COMMAND YET.");
            }
        }

        scanner.close();
        list.clear();
    }

    private static void greet() {
        System.out.println(logo + "INITIATING BURGERBURGLAR v0.0\n" +
                "------------------------------------------------------------------100%\n");
        System.out.println("BURGER SAYS HI.\nWHAT CAN BURGER DO FOR YOU?");
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
        list.add(t);
        printAddMessage(t);
    }

    private static void handleDeadline(String input) {
        String[] parts = input.split("/by", 2);
        Task t = new Deadline(parts[0].trim(), parts.length > 1 ? parts[1].trim() : "unspecified");
        list.add(t);
        printAddMessage(t);
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
        list.add(t);
        printAddMessage(t);
    }

    private static void mark(String input, boolean done) {
        try {
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1; // convert to 0-based
            Task task = list.get(index);
            task.setDone(done);

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
        System.out.print(lineBreak);
        System.out.println("HERE'S WHAT'S LEFT TO DO:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println(lineBreak);
    }

    private static void printAddMessage(Task t) {
        System.out.print(lineBreak);
        System.out.println("YOU BETTER FINISH THAT SOON. BURGERS.");
        System.out.println("ADDED: " + t);
        System.out.println("NOW YOU HAVE " + list.size() + " TASKS.");
        System.out.println(lineBreak);
    }

}
