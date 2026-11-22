# JavaFX GUI Exploration

A hands-on learning repository for building desktop applications with Java and JavaFX. It is a collection of small, runnable experiments—not a production application.

## Quick start

Choose either starter (JDK 21 required; JavaFX is resolved by the build):

```sh
cd templates/maven-starter && mvn javafx:run
# or, from the repository root:
cd templates/gradle-starter && gradle run
```

Both starters provide the same small application: FXML and CSS, a bound greeting, and a background `Task` with progress and status feedback. GUI execution requires a working desktop session; headless environments may compile but cannot display a window.

## Repository map

- `templates/maven-starter/` — Maven baseline.
- `templates/gradle-starter/` — Gradle baseline.
- `explorations/` — self-contained topic experiments; see its README for conventions.
- `docs/` — [documentation index](docs/README.md), [build/run guide](docs/build-and-run.md), [experiment conventions](docs/conventions.md), [troubleshooting](docs/troubleshooting.md), and [verified findings](docs/findings.md).
- `TODOS.md` — improvement and exploration backlog.

## Working with the starters

Run `mvn package` or `gradle build` in the corresponding starter directory to compile/package. These builds do not create self-contained installers. To begin an experiment, copy one starter into `explorations/NN-short-topic/`, then update its project name, package, title, and documentation. Keep the templates generic and record useful findings in `docs/findings.md`.

The baseline targets Java 21 and JavaFX 21, uses classpath mode (no `module-info.java`), and uses a launcher class for JavaFX startup. See each template's README for details. Tool versions recorded in findings describe the environment where the initial baseline was verified, not a guarantee for every platform.

## Project principles

- Keep each experiment small, reproducible, and runnable with one command.
- State the question being explored and document what was learned.
- Avoid machine-specific paths and unpublished dependencies.
- Prefer clarity over production-framework complexity.
