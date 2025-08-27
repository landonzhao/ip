package burgerburglar;

public class BurgerBurglar {
    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;
    private static final String VERSION = "v0.9";
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


    public BurgerBurglar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = storage.load();
        } catch (BurgerException e) {
            ui.showError("Could not load saved tasks. Starting with empty list.");
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    public void run() {
        ui.showWelcome(LOGO, VERSION); // prints welcome + manual

        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BurgerException e) {
                ui.showError(e.getMessage());  // only generic error messages
            }
        }
    }

    public static void main(String[] args) {
        new BurgerBurglar("data/burgerburglar.txt").run();
    }
}
