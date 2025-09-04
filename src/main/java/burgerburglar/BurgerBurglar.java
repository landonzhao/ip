package burgerburglar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The main class of the BurgerBurglar application.
 * <p>
 * Responsible for initializing the UI, loading tasks from storage,
 * and running the main program loop.
 */
public class BurgerBurglar {
    /** Current version of BurgerBurglar. */
    private static final String VERSION = "v0.9";

    /** Line separator used for UI messages. */
    private static final String LINE_BREAK =
            "______________________________________________________________________\n";

    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;


    /**
     * Constructs a new BurgerBurglar instance.
     * Initializes the UI, loads tasks from the given file path, and sets up storage.
     *
     * @param filePath The path of the file to load/save tasks.
     */
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

    public BurgerBurglar() {
        this("data/burgerburglar.txt");
    }

    /**
     * Starts the main program loop.
     * Displays the welcome message, then repeatedly reads user input
     * and executes commands until an exit command is issued.
     */
    public void run() {
        String logo = loadLogo();
        ui.showWelcome(logo, VERSION); // prints welcome + manual

        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BurgerException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Loads the BURGERBURGLAR logo from data/logo.txt.
     */
    private static String loadLogo() {
        try {
            return new String(Files.readAllBytes(Paths.get("data/logo.txt")));
        } catch (IOException e) {
            return "BURGERBURGLAR LOGO NOT FOUND!";
        }
    }

    /**
     * Entry point of the application.
     *
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        new BurgerBurglar("data/burgerburglar.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (BurgerException e) {
            return e.getMessage();
        }
    }
}
