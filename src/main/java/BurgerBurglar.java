import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BurgerBurglar {
    private static List<String> list = new ArrayList<>();
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
            } else {
                addTask(input);
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

    private static void addTask(String task) {
        list.add(task);
        System.out.print(lineBreak);
        System.out.println("added: " + task);
        System.out.println(lineBreak);
    }

    private static void showList() {
        System.out.print(lineBreak);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println(lineBreak);
    }
}
