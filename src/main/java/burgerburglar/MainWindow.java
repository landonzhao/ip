package burgerburglar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window of the BurgerBurglar GUI.
 */
public class MainWindow {

    @FXML
    private AnchorPane root;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private BurgerBurglar burgerburglar;

    // Placeholder images (replace with actual file paths/resources later)
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image burgerImage = new Image(this.getClass().getResourceAsStream("/images/burger.png"));

    @FXML
    public void initialize() {
        // Automatically scroll to the bottom when new dialog is added
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBurgerBurglar(BurgerBurglar b) {
        this.burgerburglar = b;
    }

    /**
     * Handles user input, sends it to the backend, and displays dialog boxes.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        // Call backend for response
        String response = burgerburglar.getResponse(input);

        // Add dialog boxes
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBurgerDialog(response, burgerImage)
        );

        // Clear input field
        userInput.clear();
    }

    /**
     * Generates a response using the BurgerBurglar backend.
     * Currently just echoes the input; replace with actual command execution.
     *
     * @param input The user's input
     * @return Response from BurgerBurglar
     */
    private String getResponse(String input) {
        if (burgerburglar == null) {
            return input; // fallback if backend not set
        }
        try {
            // Execute command and capture output
            // For GUI, you might modify BurgerBurglar to return a String response
            // For now, simple placeholder:
            return input; // remove "BurgerBurglar says:" prefix
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

