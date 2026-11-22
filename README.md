# JavaFX GUI Exploration

A practical learning lab for desktop GUI development with Java and JavaFX. This repository offers reusable Maven and Gradle starters, a convention for focused experiments, technical notes, and CI builds. It is intended for learning and comparison—not as a production application framework.

## What this repository offers

- **Two equivalent starter templates** so you can explore JavaFX using either Maven or Gradle.
- **A runnable JavaFX sample** demonstrating FXML layout/controller wiring, CSS, observable property binding, and a background `Task` reporting progress and status.
- **A home for independent experiments** with a recommended structure for recording goals, observations, and takeaways.
- **Practical documentation** for build/run, experiment conventions, troubleshooting, and verified environment findings.
- **GitHub Actions CI** that builds both starters on pushes and pull requests (and can be started manually).
- **A prioritized backlog** of planned additions such as wrappers, tests, further JavaFX experiments, and packaging.

> The `explorations/` directory currently contains its guide, not completed topic experiments. See [TODOs](TODOS.md) for planned work.

## Quick start

Requires JDK 21 and either Maven or Gradle. JavaFX dependencies are resolved by the build; a separately installed JavaFX SDK is not required for these commands. A graphical desktop session is needed to see the application window.

### Maven

```sh
cd templates/maven-starter
mvn javafx:run
```

### Gradle

```sh
cd templates/gradle-starter
gradle run
```

In the sample window, edit the name field to see a bound greeting, then run the demo task to observe background progress and status updates. To compile/package instead of opening the GUI, run `mvn package` or `gradle build` from the matching starter directory. The resulting JAR is not a self-contained installer.

## Starter contents

Both templates have the same package and application structure:

```text
src/main/java/dev/exploration/starter/
  App.java              # JavaFX Application and view setup
  Launcher.java         # main entry point
  MainController.java   # bindings and background Task demonstration
src/main/resources/dev/exploration/starter/
  main.fxml             # view layout
  styles.css            # visual styling
```

The baseline targets Java 21 and JavaFX 21, uses classpath mode (no `module-info.java`), and loads the UI from FXML. Details and commands: [Maven starter](templates/maven-starter/README.md) · [Gradle starter](templates/gradle-starter/README.md).

## Start an experiment

Copy either starter into a numbered topic folder and tailor it to one question:

```sh
cp -r templates/maven-starter explorations/01-layouts-and-controls
```

Update the project name, package, title, and README; remove demo elements unrelated to the experiment. Use the [experiment conventions](explorations/README.md) and include a clear goal, run instructions, things to try, observations, and takeaway. Keep experiments self-contained and avoid machine-specific paths.

## Repository map

| Path | Purpose |
|---|---|
| `templates/maven-starter/` | Canonical Maven JavaFX starter |
| `templates/gradle-starter/` | Equivalent Gradle starter |
| `explorations/` | Topic experiments (currently guide only) |
| `.github/workflows/build.yml` | CI build for both starter templates |
| `docs/README.md` | Documentation index |
| `docs/build-and-run.md` | Prerequisites and build/run instructions |
| `docs/conventions.md` | Experiment design and documentation conventions |
| `docs/troubleshooting.md` | Common build, display, and FXML issues |
| `docs/findings.md` | Historical toolchain verification and findings |
| `TODOS.md` | Prioritized backlog and open work |

## Documentation and project status

Start at the [documentation index](docs/README.md). The toolchain versions in [findings](docs/findings.md) record an initial verification environment; they are not a cross-platform compatibility guarantee. See [TODOs](TODOS.md) for work not yet implemented. Builds in CI validate compilation/package tasks, not GUI rendering or native installers.

## Principles

Keep examples small, runnable, and focused. Prefer clear JavaFX fundamentals over unnecessary framework complexity, and record useful lessons so each experiment contributes to the repository.
