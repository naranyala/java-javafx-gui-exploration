# JavaFX GUI Exploration

A practical learning lab for desktop GUI development with Java and JavaFX. Use it to compare build tools, study JavaFX patterns in small runnable projects, and record what works. This is a learning repository—not a production framework or a collection of supported libraries.

## Choose your path

- **New to this repository?** Run one of the starters below, then explore its FXML, controller, and CSS.
- **Trying a JavaFX concept?** Follow the [experiment guide](explorations/README.md) and [project conventions](docs/conventions.md) to make a focused, runnable example.
- **Looking at a prototype?** Browse the experiments and code examples below. They are investigation material, not production-ready APIs.
- **Need setup or troubleshooting help?** Start with the [documentation index](docs/README.md).

## What’s included

- **Two copyable starters:** Maven and Gradle projects with equivalent JavaFX 21 / JDK 21 examples. Each has an `Application` entry point, FXML view, controller, CSS, property binding, and a background `Task` demo.
- **Accessibility starting points:** a visible label associated with its input, accessible names for controls, and keyboard/focus review guidance. These are a baseline—not a claim of complete accessibility.
- **Three standalone Maven experiments:** async task lifecycle, controller cleanup, and typed preferences. Each has a README and a focused prototype.
- **Practical project notes:** build/run instructions, experiment conventions, troubleshooting, and dated findings.
- **CI:** builds both starters and all three experiments; runs the pure-Java tests in experiments 06 and 07. GUI rendering and native installers are not tested.

## Run a starter

Requires JDK 21 and either Maven or Gradle. JavaFX dependencies are resolved by the build, so a separate JavaFX SDK is not required. A graphical desktop session is needed to display the window.

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

In the window, edit the name field to see the greeting update, then run the background task to observe progress and status. Use Tab/Shift+Tab to move between controls and Enter/Space to activate the button. The resulting JAR from `mvn package` or `gradle build` is not a self-contained installer.

More commands and starter-specific notes: [Maven starter](templates/maven-starter/README.md) · [Gradle starter](templates/gradle-starter/README.md).

## Patterns you can explore

The excerpts below highlight patterns implemented in the linked projects. They omit imports and some controller/FXML wiring; use the source links for complete context.

### 1. Derive UI text from an observable property

The starter recomputes the greeting whenever the text field changes. JavaFX owns the binding relationship, so the label updates without a manual text listener:

```java
greetingLabel.textProperty().bind(Bindings.createStringBinding(() -> {
    String name = nameField.getText() == null ? "" : nameField.getText().trim();
    return name.isEmpty() ? "Hello, explorer." : "Hello, " + name + ".";
}, nameField.textProperty()));
```

See [`MainController.java`](templates/maven-starter/src/main/java/dev/exploration/starter/MainController.java).

### 2. Run background work and report progress

A JavaFX `Task` performs work away from the JavaFX Application Thread and publishes progress and messages for UI bindings. The async experiment also provides cancel and deliberate-failure controls.

```java
Task<Void> task = new Task<>() {
    @Override
    protected Void call() throws Exception {
        for (int step = 1; step <= 10; step++) {
            if (isCancelled()) return null;
            Thread.sleep(150); // interruptible demonstration work
            updateProgress(step, 10);
            updateMessage("Working... step " + step);
        }
        return null;
    }
};

progressBar.progressProperty().bind(task.progressProperty());
task.setOnSucceeded(event -> showStatus("Finished"));
task.setOnFailed(event -> showStatus("Failed: " + task.getException()));
task.setOnCancelled(event -> showStatus("Cancelled"));

Thread worker = new Thread(task, "demo-task");
worker.setDaemon(true);
worker.start();
```

Call `task.cancel(true)` to request cancellation. Cancellation is cooperative, and interruptible operations may throw `InterruptedException`. The experiment serializes requests; **latest-wins/stale-result suppression is not implemented**. See [`05-async-task-lifecycle`](explorations/05-async-task-lifecycle/) for the runnable version.

### 3. Tie listener cleanup to a view’s lifetime

The lifecycle prototype owns `AutoCloseable` cleanup actions and closes them in reverse registration order. Keep the same listener instance for registration and removal:

```java
SubscriptionScope scope = new SubscriptionScope();
ChangeListener<String> listener = (observable, oldValue, newValue) -> refresh(newValue);
model.valueProperty().addListener(listener);
scope.own(() -> model.valueProperty().removeListener(listener));

// When the view/controller is discarded:
scope.close();
```

See [`SubscriptionScope.java`](explorations/06-controller-lifecycle/src/main/java/dev/exploration/starter/SubscriptionScope.java). The scope is an experimental helper, not a supported library API.

### 4. Add typed access around JDK preferences

The preferences prototype uses typed keys for defaults, codecs, and validation while keeping storage on `java.util.prefs.Preferences`:

```java
var fontSize = PreferenceKey.integerKey(
        "fontSize", 14, size -> size >= 8 && size <= 72);
var settings = new TypedPreferences(Preferences.userNodeForPackage(App.class));

int currentSize = settings.get(fontSize); // default when absent or malformed
settings.put(fontSize, 18);
settings.flush(); // explicit; may perform blocking I/O
```

See [`PreferenceKey.java`](explorations/07-typed-preferences/src/main/java/dev/exploration/starter/PreferenceKey.java) and [`TypedPreferences.java`](explorations/07-typed-preferences/src/main/java/dev/exploration/starter/TypedPreferences.java). The prototype does not provide migrations or an atomic file backend.

### 5. Give controls accessible names

Associate a visible label with its input. Give controls an accessible name when their purpose is not clear from visible text:

```xml
<Label text="Name" labelFor="$nameField"/>
<TextField fx:id="nameField" promptText="Type a name..."
           accessibleText="Name"/>
<ProgressBar fx:id="progressBar"
             accessibleText="Background task progress"/>
```

See the [complete starter FXML](templates/maven-starter/src/main/resources/dev/exploration/starter/main.fxml) and the [accessibility checklist](docs/conventions.md). Check focus visibility and screen-reader announcements on the target platform; FXML attributes alone do not prove accessibility.

## Current experiments

Each experiment is an independent Maven project based on the starter. Their scope and current limitations are summarized here:

| Experiment | What it demonstrates | Still to investigate |
|---|---|---|
| [`05-async-task-lifecycle`](explorations/05-async-task-lifecycle/) | Progress, cancellation, and a deliberate failure using JavaFX `Task`. | Success/cancel/failure behavior under verification; `Service` and `CompletableFuture` comparison; stale-result policy. |
| [`06-controller-lifecycle`](explorations/06-controller-lifecycle/) | `SubscriptionScope` cleanup ownership, with tests for ordering, idempotence, late registration, failures, and reentrant cleanup. | JavaFX listener/binding adapters, repeated view disposal, and leak-sensitive behavior. |
| [`07-typed-preferences`](explorations/07-typed-preferences/) | Typed keys and a facade over JDK `Preferences`, with tests for defaults, codecs, invalid values, removal, and flush. | Persistence/error behavior across environments, key compatibility, and whether migration support is needed. |

Build or test an experiment from the repository root with Maven:

```sh
mvn -f explorations/05-async-task-lifecycle/pom.xml package
mvn -f explorations/06-controller-lifecycle/pom.xml verify
mvn -f explorations/07-typed-preferences/pom.xml verify
```

These projects have not been built locally in the current environment because Java and Maven are unavailable. CI is configured to build them; check the workflow result before treating their code or tests as verified. Unit tests cover pure-Java behavior and do not open a JavaFX window.

## Create a new experiment

Copy a starter into a numbered topic folder, then tailor it to one question:

```sh
cp -r templates/maven-starter explorations/01-layouts-and-controls
```

Rename the project/package, update the window title and README, and remove starter UI unrelated to the topic. Each experiment README should explain its **goal**, exact **run** command, what to **try**, what you **observed**, and the **takeaway**. Also review accessible names, keyboard/focus behavior, status/progress communication, resizing, and assistive technology where practical. See [experiment conventions](docs/conventions.md); keep projects self-contained and avoid machine-specific paths.

## Starter structure and technical baseline

Both starters share this layout:

```text
src/main/java/dev/exploration/starter/
  App.java              # JavaFX Application and view setup
  Launcher.java         # main entry point
  MainController.java   # bindings and background Task demo
src/main/resources/dev/exploration/starter/
  main.fxml             # view, controls, and accessible names
  styles.css            # visual styling
```

The starters target Java 21 and JavaFX 21, use classpath mode (no `module-info.java`), and load the view from FXML. FXML reflection works in the unnamed module without `--add-opens`. The initial tested environment and known caveats are recorded in [findings](docs/findings.md); they are not a cross-platform compatibility guarantee.

## Repository guide

| Path | What you’ll find |
|---|---|
| [`templates/`](templates/) | Maven and Gradle starter projects |
| [`explorations/`](explorations/) | Current experiments and instructions for creating more |
| [`docs/README.md`](docs/README.md) | Documentation index |
| [`docs/build-and-run.md`](docs/build-and-run.md) | Toolchain prerequisites and commands |
| [`docs/conventions.md`](docs/conventions.md) | Experiment format and accessibility/keyboard checklist |
| [`docs/troubleshooting.md`](docs/troubleshooting.md) | Common JavaFX build, display, and FXML problems |
| [`docs/findings.md`](docs/findings.md) | Dated toolchain verification and technical findings |
| [`.github/workflows/build.yml`](.github/workflows/build.yml) | CI for starters and experiments |
| [`TODOS.md`](TODOS.md) | Prioritized backlog |

This repository focuses on learning and validating JavaFX workflows. If an experiment suggests a reusable-library gap, compare existing solutions and record the research/API decision in [javafx-companion-libs](https://github.com/naranyala/javafx-companion-libs) rather than treating a prototype as a commitment to publish.
