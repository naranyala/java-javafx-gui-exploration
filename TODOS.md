# Backlog

Track improvements to the JavaFX learning repository. Keep completed work out of the active list; add evidence and platform details to `docs/findings.md` when behavior is verified.

## Starter quality

- [ ] Add Maven and Gradle wrappers, pinning tested wrapper versions.
- [ ] Align dependency/plugin versions and document the supported JDK/JavaFX matrix.
- [ ] Add focused unit tests for logic extracted from the FXML controller.
- [ ] Add an automated compile/package check for both starters.
- [ ] Evaluate a minimal logging approach and establish exception-reporting conventions.
- [ ] Document accessibility basics (keyboard navigation, labels, focus).
- [ ] Add icons and a consistent window title/app identity.

## Experiments

- [ ] Layouts and controls: `BorderPane`, `VBox`, `HBox`, `GridPane`, `StackPane`; resize and sizing behavior.
- [ ] FXML: nested views with `fx:include`, controller communication, and equivalent Java-built UI.
- [ ] Binding/MVVM: properties, computed and bidirectional bindings, validation.
- [ ] CSS: runtime light/dark theme, pseudo-classes, looked-up colors.
- [ ] Concurrency: `Task`, `Service`, cancellation, progress, and the consequences of blocking the FX thread.
- [ ] Data views: editable `TableView`, cell factories, filtering/sorting, and view comparisons.
- [ ] Charts, media, and `WebView`: verify platform support and document limitations.
- [ ] Testing: evaluate TestFX, headless options, and a repeatable GUI smoke test.

## Distribution

- [ ] Compare classpath and module-path builds, including FXML reflection trade-offs.
- [ ] Build a runtime image with `jlink` and installer with `jpackage`.
- [ ] Record native dependencies and test distribution on target operating systems.

## Repository maintenance

- [ ] Decide whether experiments remain independent or become a multi-project build.
- [ ] Add screenshots where they help explain an experiment.
- [ ] Keep findings dated, concise, and reproducible.
