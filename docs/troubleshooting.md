# Troubleshooting

## Build fails before JavaFX starts

- Confirm the selected JDK: `java -version` and `mvn -version` (or `gradle --version`). The starter targets Java 21.
- Check network access to Maven Central if JavaFX artifacts cannot be resolved.
- Run commands from the relevant starter directory and use the command documented in its README.

## Build succeeds but no window appears

- JavaFX needs a graphical session and native platform libraries. A headless shell or disconnected remote session may not have a usable display.
- On Linux, confirm the display environment/session is available and that GTK and graphics libraries required by JavaFX are installed.
- Look at the full exception output; native-library loading and display initialization failures usually provide more detail than the final launch error.

## FXML controller or resource cannot be found

- Confirm FXML/CSS are under `src/main/resources` and resource paths match the package layout.
- Check `fx:controller` and `FXMLLoader` paths for spelling and package changes after copying a starter.
- If converting to named modules, review JavaFX FXML reflection requirements and open the controller package to `javafx.fxml`. The current starter intentionally uses the classpath and has no `module-info.java`.

## Still stuck?

Record the OS, JDK/JavaFX versions, build command, and complete relevant error in an issue or finding. Avoid copying secrets, user-specific paths, or environment credentials into repository documentation.
