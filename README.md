# Java JavaFX GUI Exploration

A hands-on sandbox for exploring **desktop GUI development in Java with JavaFX**.
The goal is learning and comparison through small, runnable experiments — not a
production application.

## Status

Toolchain verified on AlmaLinux 10.2 with Wayland/XWayland:

| Tool | Version |
|---|---|
| JDK (Temurin) | 21.0.13.1 LTS |
| JavaFX SDK | 21.0.13 |
| Maven | 3.9.16 |
| Gradle | 9.3.1 |

The starter compiles and opens a real window through Maven, Gradle, and the raw
JavaFX SDK.

## Repository layout

```
.
├── README.md
├── TODOS.md
├── docs/
│   └── findings.md              # what we learned, decisions, gotchas
├── explorations/
│   └── README.md                # convention for individual experiments
└── templates/
    ├── maven-starter/           # canonical Maven baseline
    │   ├── pom.xml
    │   ├── README.md
    │   └── src/main/...
    └── gradle-starter/          # same app, Gradle build
        ├── build.gradle
        ├── settings.gradle
        ├── README.md
        └── src/main/...
```

## Quick start

### Maven starter

```bash
cd templates/maven-starter
mvn javafx:run
```

### Gradle starter

```bash
cd templates/gradle-starter
gradle run
```

Both starters launch the same app: a small FXML + CSS window with live binding
and a background-task demo.

## What the starter demonstrates

- **FXML layout + controller** (`main.fxml`, `MainController`)
- **CSS theming** (`styles.css`)
- **Property binding** (name field → greeting, busy state → button disable)
- **Background work** (`Task`, progress bar, status message, activity log)
- **Packaging-friendly entry point** (`Launcher`, no `Application` subclass as
  the main class)
- **Clean project structure** ready to copy for a new exploration

## Conventions

- One topic per folder: `explorations/NN-short-topic/`
- Every exploration is self-contained and runnable
- Every exploration has a `README.md` with:
  **Goal → What to try → Result → Takeaway**
- Prefer the smallest example that demonstrates the idea
- Record non-obvious findings in [`docs/findings.md`](docs/findings.md)
- Keep the starter free of experiment-specific code

## Packaging notes

The starter exposes `dev.exploration.starter.Launcher` so tools such as
`jpackage` can launch the app without the JavaFX launcher warning. A typical
sequence is:

```bash
mvn -q -DskipTests package
jpackage --type rpm \
  --name JavaFXStarter \
  --input target \
  --main-jar javafx-starter-0.1.0-SNAPSHOT.jar \
  --main-class dev.exploration.starter.Launcher
```

Native packaging is still on the backlog; see [`TODOS.md`](TODOS.md).

## Toolchain

```text
JAVA_HOME   = ~/.local/opt/jdk-21
JAVAFX_HOME = ~/.local/opt/javafx-sdk-21
```

Maven and Gradle download JavaFX from Maven Central. `JAVAFX_HOME` is only
needed when compiling or running directly with `javac` / `java`:

```bash
javac --module-path "$JAVAFX_HOME/lib" --add-modules javafx.controls,javafx.fxml ...
```

## Next steps

See [`TODOS.md`](TODOS.md) for the exploration backlog.
