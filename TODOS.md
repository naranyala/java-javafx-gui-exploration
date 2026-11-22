# TODOS

Backlog for the JavaFX exploration project. Check items off as they are
completed; keep notes in [`docs/findings.md`](docs/findings.md).

## Done

- [x] Verify JDK, JavaFX SDK, Maven, Gradle, and GUI runtime libraries
- [x] Validate Maven, Gradle, and raw-SDK launch paths with a real window
- [x] Create a reusable Maven starter (FXML + CSS + binding + Task)
- [x] Create a reusable Gradle starter with the same application
- [x] Document purpose, layout, conventions, and toolchain
- [x] Add project `.gitignore`

## Starter hardening

- [ ] Add Maven Wrapper (`mvnw`) so the starter does not depend on a global Maven
- [ ] Add Gradle Wrapper (`gradlew`) so the starter does not depend on a global Gradle
- [ ] Add a unit test for the controller/model logic
- [ ] Add an application icon and window title convention
- [ ] Decide on module path (`module-info.java`) vs. classpath for the starter
- [ ] Add a minimal logging setup (e.g., `java.util.logging`)

## Explorations

### 01 — Layouts and controls

- [ ] Build a gallery of core layouts (`BorderPane`, `VBox`, `HBox`, `GridPane`, `StackPane`)
- [ ] Compare `AnchorPane` constraints with layout panes
- [ ] Demonstrate resizing behavior and min/pref/max sizes

### 02 — FXML and controllers

- [ ] Split a view into nested FXML files with `fx:include`
- [ ] Pass data between parent and child controllers
- [ ] Compare FXML with equivalent imperative Java

### 03 — Binding and MVVM

- [ ] Introduce a view model with JavaFX properties
- [ ] Show one-way, bidirectional, and computed bindings
- [ ] Add validation with binding and visual error states

### 04 — CSS and theming

- [ ] Build a light and dark theme switchable at runtime
- [ ] Style a custom control with pseudo-classes
- [ ] Compare inline styles, stylesheets, and CSS variables (`-fx-` looked-up colors)

### 05 — Background work and responsiveness

- [ ] Demonstrate `Task`, `Service`, and `Platform.runLater`
- [ ] Add cancellation and progress reporting
- [ ] Show what happens when blocking work runs on the FX thread

### 06 — Data views

- [ ] Explore `TableView` with editable cells and custom cell factories
- [ ] Add filtering and sorting through `FilteredList` / `SortedList`
- [ ] Compare `ListView`, `TreeView`, and `TableView`

### 07 — Charts and media

- [ ] Build a small chart dashboard
- [ ] Try audio/video playback with `javafx.media`
- [ ] Evaluate `WebView` for embedded content

### 08 — Testing

- [ ] Evaluate TestFX for UI tests
- [ ] Document headless/CI limitations and options (Monocle)
- [ ] Add a repeatable smoke test for the starter

## Packaging and distribution

- [ ] Build a runtime image with `jlink`
- [ ] Package a native installer with `jpackage`
- [ ] Document required native dependencies for the target distro
- [ ] Compare classpath and module-path packaging

## Documentation

- [ ] Add a screenshot of the starter to the README
- [ ] Record JavaFX version compatibility notes (JDK 21 vs. newer JDKs)
- [ ] Summarize lessons learned at the end of each exploration

## Open questions

- Should explorations share one multi-module build, or stay independent?
- Is a modular (`module-info.java`) baseline worth the FXML/reflection trade-off?
- Which packaging format matters most: `.deb`, `.rpm`, AppImage, or a runtime image?
