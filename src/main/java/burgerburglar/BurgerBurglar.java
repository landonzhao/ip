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

    /** Default file path for saving and loading tasks. */
    private static final String DEFAULT_FILE_PATH = "data/burgerburglar.txt";

    /** File path for the application logo. */
    private static final String LOGO_FILE_PATH = "data/logo.txt";

    /** Fallback message if logo cannot be loaded. */
    private static final String LOGO_NOT_FOUND = "BURGERBURGLAR LOGO NOT FOUND!";

    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a new BurgerBurglar instance.
     *
     * @param filePath The path of the file to load/save tasks.
     */
    public BurgerBurglar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = initializeTasks();
    }

    public BurgerBurglar() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Initializes the task list by attempting to load from storage.
     */
    private TaskList initializeTasks() {
        try {
            return storage.load();
        } catch (BurgerException e) {
            ui.showError("Could not load saved tasks. Starting with empty list.");
            return new TaskList();
        }
    }

    /**
     * Starts the main program loop.
     */
    public void run() {
        showWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            isExit = processNextCommand();
        }
    }

    /**
     * Displays the welcome message with the logo and version.
     */
    private void showWelcomeMessage() {
        String logo = loadLogo();
        ui.showWelcome(logo, VERSION);
    }

    /**
     * Reads and executes the next user command.
     *
     * @return true if the command signals exit, false otherwise
     */
    private boolean processNextCommand() {
        String input = ui.readCommand();
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            return command.isExit();
        } catch (BurgerException e) {
            ui.showError(e.getMessage());
            return false;
        }
    }

    /**
     * Loads the BURGERBURGLAR logo from file.
     */
    private static String loadLogo() {
        try {
            return Files.readString(Paths.get(LOGO_FILE_PATH));
        } catch (IOException e) {
            return LOGO_NOT_FOUND;
        }
    }

    /**
     * Entry point of the application.
     *
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        new BurgerBurglar().run();
    }

    /**
     * Provides a response for GUI integration.
     *
     * @param input user command
     * @return program response as a string
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (BurgerException e) {
            return e.getMessage();
        }
    }
}
