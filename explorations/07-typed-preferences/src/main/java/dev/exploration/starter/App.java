package dev.exploration.starter;

import java.util.Objects;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application entry point.
 *
 * <p>Layout lives in {@code main.fxml}, styling in {@code styles.css}, and
 * behaviour in {@link MainController}. Use {@link Launcher} as the main class
 * for packaging tools such as jpackage.
 */
public class App extends Application {

    private static final String FXML = "main.fxml";
    private static final String CSS = "styles.css";

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                Objects.requireNonNull(App.class.getResource(FXML), "Missing " + FXML));
        Scene scene = new Scene(loader.load(), 760, 520);
        scene.getStylesheets().add(
                Objects.requireNonNull(App.class.getResource(CSS), "Missing " + CSS)
                        .toExternalForm());

        stage.setTitle("JavaFX Exploration Starter");
        stage.setMinWidth(600);
        stage.setMinHeight(440);
        stage.setScene(scene);
        stage.show();

        // Test hook: lets a smoke test close the window automatically.
        if ("1".equals(System.getenv("STARTER_AUTOCLOSE"))) {
            Platform.runLater(Platform::exit);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
