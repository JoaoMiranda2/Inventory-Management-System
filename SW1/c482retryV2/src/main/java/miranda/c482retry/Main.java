package miranda.c482retry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.Objects;

/**
 * The Main class serves as the entry point for "Inventory Management System" application.
 * (FUTURE ENHANCEMENT) adding some sort of login form before displaying the "MainForm.fxml".
 * Save input data to specific user logins to increase privacy.
 *
 * @author Joao Marcelo Martins Miranda
 *
 */
//Javadoc file in src/main/javadoc
public class Main extends Application {

    /**
     * The following method is the entry point for the JavaFX application.
     * (RUNTIME ERROR PREVENTION) used ".requireNonNull" to help prevent a "NullPointerException" at runtime.
     * @param stage The primary stage for the ui.
     * @throws IOException If an error occurs while loading the MainForm.
     */
    @Override
    public void start(Stage stage) throws IOException{
        // Loads the "MainForm" using FXMLLoader

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));

        // Creates a scene with the loaded fxml file.
        Scene scene = new Scene(root);

        // Sets a title for the window
        stage.setTitle("Inventory Management System");

        // Sets the scene for the Primary Stage
        stage.setScene(scene);

        // Shows the Primary Stage
        stage.show();
    }

    /**
     * The main method is the entry point of the Java application.
     *
     * @param args Command-line arguments (not used in this application).
     */

    public static void main(String[] args) {
        launch();
    }
}