package dev.exploration.starter;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

/** Demonstrates JavaFX Task progress, cancellation, and failure delivery. */
public class MainController {
    private static final int TASK_STEPS = 10;
    private static final long STEP_DELAY_MILLIS = 150;

    @FXML private TextField nameField;
    @FXML private Label greetingLabel;
    @FXML private Button runTaskButton;
    @FXML private Button cancelTaskButton;
    @FXML private CheckBox failTaskCheckBox;
    @FXML private ProgressBar progressBar;
    @FXML private Label statusLabel;
    @FXML private ListView<String> logList;

    private final BooleanProperty busy = new SimpleBooleanProperty(false);
    private Task<Void> activeTask;

    @FXML
    private void initialize() {
        greetingLabel.textProperty().bind(Bindings.createStringBinding(
                () -> {
                    String name = nameField.getText() == null ? "" : nameField.getText().trim();
                    return name.isEmpty() ? "Hello, explorer." : "Hello, " + name + ".";
                }, nameField.textProperty()));
        runTaskButton.disableProperty().bind(busy);
        failTaskCheckBox.disableProperty().bind(busy);
        cancelTaskButton.disableProperty().bind(busy.not());
        logList.getItems().add("Starter ready.");
    }

    @FXML
    private void onRunTask() {
        if (busy.get()) return;

        Task<Void> task = createDemoTask(failTaskCheckBox.isSelected());
        activeTask = task;
        busy.set(true);
        progressBar.progressProperty().bind(task.progressProperty());
        statusLabel.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(event -> finishTask(task, "Background work finished."));
        task.setOnFailed(event -> finishTask(task, "Background work failed: " + describe(task.getException())));
        task.setOnCancelled(event -> finishTask(task, "Background work cancelled."));

        Thread worker = new Thread(task, "async-task-experiment");
        worker.setDaemon(true);
        worker.start();
    }

    @FXML
    private void onCancelTask() {
        Task<Void> task = activeTask;
        if (task != null) task.cancel(true);
    }

    private Task<Void> createDemoTask(boolean failAtMidpoint) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int step = 1; step <= TASK_STEPS; step++) {
                    if (isCancelled()) return null;
                    Thread.sleep(STEP_DELAY_MILLIS);
                    if (failAtMidpoint && step == TASK_STEPS / 2) {
                        throw new IllegalStateException("Requested demonstration failure");
                    }
                    updateProgress(step, TASK_STEPS);
                    updateMessage("Working... step " + step + " of " + TASK_STEPS);
                }
                return null;
            }
        };
    }

    private void finishTask(Task<?> task, String message) {
        if (activeTask != task) return;
        activeTask = null;
        progressBar.progressProperty().unbind();
        statusLabel.textProperty().unbind();
        progressBar.setProgress(task.getProgress());
        statusLabel.setText(message);
        logList.getItems().add(message);
        busy.set(false);
    }

    private static String describe(Throwable error) {
        return error == null || error.getMessage() == null ? "unknown error" : error.getMessage();
    }
}
