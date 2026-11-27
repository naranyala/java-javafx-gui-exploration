# Gradle Starter

The same JavaFX baseline as `templates/maven-starter`, built with Gradle and the
`org.openjfx.javafxplugin`.

## Run

```bash
gradle run
```

The JavaFX plugin adds JavaFX 21.0.13 to the module path and configures the run
task. A JDK 21 toolchain is requested, so a compatible JDK must be available.

## Build

```bash
gradle build
```

## Smoke test

```bash
STARTER_AUTOCLOSE=1 gradle -q run
```

## Accessibility and keyboard use

The name field has an associated visible label, and the progress, status, and activity controls have accessible names. Use Tab/Shift+Tab to move through the controls and Enter/Space to activate the task button. Check actual focus visibility and screen-reader announcements on your target platform; FXML names alone do not guarantee accessible behavior.

## Structure

```text
build.gradle
settings.gradle
src/main/java/dev/exploration/starter/
  App.java
  Launcher.java
  MainController.java
src/main/resources/dev/exploration/starter/
  main.fxml
  styles.css
```

## Notes

- The plugin version (`0.1.0`) is the bridge between Gradle and the OpenJFX
  artifacts; keep it aligned with the Gradle version when upgrading.
- Use `gradle wrapper --gradle-version 9.3.1` to add a wrapper if the project
  should not depend on a globally installed Gradle.
- The source is intentionally duplicated from the Maven starter so each template
  remains standalone and copyable.
