package burgerburglar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The JavaFX entry point for BurgerBurglar.
 * <p>
 * Responsible for loading the GUI layout and initializing the application.
 */
public class Main extends Application {

    private final BurgerBurglar burgerburglar = new BurgerBurglar();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Pass backend BurgerBurglar instance to MainWindow controller
            Scene scene = new Scene(ap);
            stage.setTitle("BurgerBurglar");
            stage.setScene(scene);
            stage.show();

            MainWindow controller = fxmlLoader.getController();
            controller.setBurgerBurglar(new BurgerBurglar("data/burgerburglar.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

