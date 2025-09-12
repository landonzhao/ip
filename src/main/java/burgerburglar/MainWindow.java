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

    private static final String USER_IMAGE_PATH = "/images/user.jpg";
    private static final String BURGER_IMAGE_PATH = "/images/burger.png";

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

    private final Image userImage = new Image(
            getClass().getResourceAsStream(USER_IMAGE_PATH)
    );
    private final Image burgerImage = new Image(
            getClass().getResourceAsStream(BURGER_IMAGE_PATH)
    );

    /**
     * Greet the user upon entry.
     */
    @FXML
    public void initialize() {
        bindAutoScroll();
    }

    public void setBurgerBurglar(BurgerBurglar burgerburglar) {
        this.burgerburglar = burgerburglar;
        showGreeting();
    }

    /**
     * Show the greeting message from BurgerBurglar.
     */
    private void showGreeting() {
        dialogContainer.getChildren().add(
                DialogBox.getBurgerDialog(burgerburglar.showGreeting(), burgerImage)
        );
    }

    /**
     * Handles user input: validates, delegates to backend, displays dialogs.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return; // guard clause
        }

        String response = burgerburglar.getResponse(input);
        showDialogs(input, response);
        clearUserInput();
    }

    /**
     * Ensures scroll pane always scrolls to the bottom as new messages are added.
     */
    private void bindAutoScroll() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Displays both user and burger dialogs.
     */
    private void showDialogs(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBurgerDialog(response, burgerImage)
        );
    }

    /**
     * Clears the user input field after sending a message.
     */
    private void clearUserInput() {
        userInput.clear();
    }
}
