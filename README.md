# JavaFX GUI Exploration

A practical learning lab for desktop GUI development with Java and JavaFX. This repository provides copyable Maven and Gradle starters, focused runnable experiments, accessibility and experiment conventions, technical notes, and CI builds. It is for learning and comparison—not a production application framework or a library catalog.

## What this repository offers

- **Maven and Gradle starters** with equivalent JavaFX 21 / JDK 21 examples. Each includes an `Application` entry point, FXML view, controller, CSS, observable-property binding, and a background `Task` demo.
- **Accessible starter patterns**: a visible label associated with the name field, accessible names for progress/status/activity controls, and keyboard/screen-reader testing guidance. These are a baseline, not a claim of complete accessibility; behavior must be checked with the target platform's assistive technology.
- **Runnable experiment projects** with a consistent README structure for goals, run commands, things to try, observations, and takeaways. Current starting points are listed below.
- **Small prototype utilities** for controller-resource cleanup and typed preferences, included as investigation material rather than supported APIs.
- **Project guidance** for building/running, experiment design, accessibility/keyboard checks, and troubleshooting.
- **Build CI** for the two starter templates, plus a prioritized backlog of future work.

## Quick start

Requires JDK 21 and either Maven or Gradle. JavaFX dependencies are resolved by the build; a separately installed JavaFX SDK is not required. A graphical desktop session is needed to see the application window.

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

Edit the name field to see the bound greeting, then run the demo task to observe background progress and status updates. Use Tab/Shift+Tab to navigate and Enter/Space to activate the button. To compile/package without opening the GUI, run `mvn package` or `gradle build` in the respective starter directory. The resulting JAR is not a self-contained installer.

See each starter README for build details and smoke-test commands: [Maven](templates/maven-starter/README.md) · [Gradle](templates/gradle-starter/README.md).

## Current experiments

Each folder is a standalone Maven project copied from the starter. These are early learning baselines/prototypes; they have **not been build-verified in the current environment** because Maven is unavailable here. In particular, their stated follow-up behavior should not be mistaken for a completed implementation or a production-ready library.

| Experiment | Focus and included material |
|---|---|
| [`05-async-task-lifecycle`](explorations/05-async-task-lifecycle/) | Starter `Task` baseline; notes frame cancellation, failure, progress, stale-result suppression, and JavaFX-thread delivery questions. |
| [`06-controller-lifecycle`](explorations/06-controller-lifecycle/) | Starter baseline plus `SubscriptionScope`, a small `AutoCloseable` cleanup-ownership prototype. |
| [`07-typed-preferences`](explorations/07-typed-preferences/) | Starter baseline plus `PreferenceKey` and `TypedPreferences`, a typed facade over JDK `Preferences` with defaults and validation. |

These projects are not yet listed as completed learning experiments: deeper interactions, automated tests, and verification remain open. See [TODOs](TODOS.md) for the roadmap. The [experiment guide](explorations/README.md) explains conventions and starting a new topic.

## Start an experiment

Copy either starter into a numbered topic folder and tailor it to one question:

```sh
cp -r templates/maven-starter explorations/01-layouts-and-controls
```

Update the project name, package, title, and README; remove unrelated demo elements. Use the [experiment conventions](docs/conventions.md) and include a goal, exact run instructions, things to try, observations, and takeaway. Check accessible names, keyboard/focus behavior, status/progress communication, resizing, and assistive technology where practical. Keep experiments self-contained and avoid machine-specific paths.

## Starter structure

Both templates share this layout:

```text
src/main/java/dev/exploration/starter/
  App.java              # JavaFX Application and view setup
  Launcher.java         # main entry point
  MainController.java   # property bindings and background Task demo
src/main/resources/dev/exploration/starter/
  main.fxml             # view, controls, and accessible names
  styles.css            # visual styling
```

The starters target Java 21 and JavaFX 21, use classpath mode (no `module-info.java`), and load the view from FXML. FXML reflection works in the unnamed module without `--add-opens`.

## Repository map

| Path | Purpose |
|---|---|
| `templates/maven-starter/` | Maven starter and its run/build/accessibility guidance |
| `templates/gradle-starter/` | Equivalent Gradle starter and guidance |
| `explorations/` | Experiment guide and current standalone project prototypes (`05`–`07`) |
| `docs/README.md` | Documentation index |
| `docs/build-and-run.md` | Prerequisites and Maven/Gradle commands |
| `docs/conventions.md` | Experiment format plus accessibility and keyboard checklist |
| `docs/troubleshooting.md` | Common build, display, and FXML issues |
| `docs/findings.md` | Dated environment verification and technical findings |
| `.github/workflows/build.yml` | CI builds for both starter templates |
| `TODOS.md` | Prioritized backlog and planned work |

## Documentation and project status

Start at the [documentation index](docs/README.md). [Findings](docs/findings.md) records the initial tested environment and recent checks; it is not a cross-platform compatibility guarantee. The accessibility FXML was XML-parsed, but screen-reader and focus rendering remain platform-specific manual checks. CI validates starter compilation/package tasks, not GUI rendering, experiment builds, or native installers.

See [TODOs](TODOS.md) for open work, including reproducible wrappers, tests, improved experiment coverage, and packaging. For possible library extraction, this repo is the experiment/prototype side of the workflow; research and library decisions belong in [javafx-companion-libs](https://github.com/naranyala/javafx-companion-libs).

## Principles

Keep examples small, runnable, accessible by design, and focused. Prefer clear JavaFX fundamentals over unnecessary framework complexity. Treat prototypes as hypotheses, verify them before drawing conclusions, and record reusable lessons so each experiment contributes to the project.
