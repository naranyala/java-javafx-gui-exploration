# Build and run

## Requirements

- JDK 21 (the current templates target Java 21).
- Maven or Gradle, depending on the starter you use.
- A graphical desktop session to display a JavaFX window. JavaFX dependencies are resolved by the build from Maven Central; a separately installed JavaFX SDK is not needed for these commands.

## Maven starter

From the repository root:

```sh
cd templates/maven-starter
mvn javafx:run
mvn package
```

## Gradle starter

```sh
cd templates/gradle-starter
gradle run
gradle build
```

`package`/`build` compile and package the application; they do not produce a self-contained installer. The generated JAR is not a fat JAR. For development, use the JavaFX Maven goal or Gradle application task, which supplies the JavaFX runtime configuration.

## Headless environments

Compilation can work without a display, but launching a window generally requires a working desktop/display server and native JavaFX dependencies. UI smoke tests and headless testing have not yet been established; consult [Findings](findings.md) and [TODOs](../TODOS.md).
