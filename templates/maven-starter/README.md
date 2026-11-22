# Maven Starter

Minimal, runnable JavaFX baseline: **FXML layout + controller + CSS + binding +
background task**.

## Run

```bash
mvn javafx:run
```

No global JavaFX SDK is required; Maven resolves JavaFX 21.0.13 from Maven
Central.

## Build

```bash
mvn package
```

The resulting jar is **not** a fat jar. Run it with the JavaFX modules on the
module path, or use `mvn javafx:run`.

## Smoke test

The window can close itself for automated checks:

```bash
STARTER_AUTOCLOSE=1 mvn -q javafx:run
```

## Structure

```text
pom.xml
src/main/java/dev/exploration/starter/
  App.java              # Application + FXML/CSS wiring
  Launcher.java         # packaging-friendly main class
  MainController.java   # bindings + background Task
src/main/resources/dev/exploration/starter/
  main.fxml
  styles.css
```

## What to copy into a new exploration

- Keep `App` and `Launcher` as-is.
- Rename the package and `artifactId`.
- Replace `main.fxml`, `styles.css`, and `MainController` with the experiment.
- Update the stage title and README.

## Notes

- Classpath mode is used deliberately; there is no `module-info.java`, so FXML
  reflection needs no `--add-opens`.
- The controller owns the demo `Task`; move it into a service/view-model as soon
  as an experiment grows.
