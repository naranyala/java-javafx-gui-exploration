# Backlog

Prioritized work for the JavaFX learning repository. Check off only work implemented and verified; record verification details in `docs/findings.md`.

## Next: reproducible baseline

- [ ] Add Maven and Gradle wrappers and pin tested versions.
- [x] Add CI to build both starters on pushes and pull requests (GitHub Actions; pending remote run).
- [ ] Add lightweight tests for starter logic; keep GUI tests separate until headless strategy is validated.
- [ ] Document tested JDK/JavaFX/OS combinations and refresh stale environment notes.

## Starter improvements

- [ ] Align dependency and plugin versions across templates where practical.
- [ ] Add accessible names, keyboard/focus guidance, and an accessibility checklist.
- [ ] Define a consistent app title and icon convention.
- [ ] Choose basic logging and exception-reporting conventions.

## Learning experiments

- [ ] Layouts and controls: sizing, resizing, and common layout panes.
- [ ] FXML: nested views, controller communication, and imperative equivalent.
- [ ] Binding/MVVM: properties, computed/bidirectional bindings, validation.
- [ ] CSS: runtime themes, pseudo-classes, looked-up colors.
- [ ] Concurrency: `Task`, `Service`, cancellation, progress, FX-thread blocking.
- [ ] Data views: editable tables, custom cells, filtering/sorting.
- [ ] Charts, media, and `WebView`; document platform limitations.

## Testing and distribution

- [ ] Evaluate TestFX and headless/CI options; add a GUI smoke test if reliable.
- [ ] Compare classpath/module-path approaches, including FXML reflection.
- [ ] Build a `jlink` runtime image and `jpackage` installer; document native dependencies.

## Repository decisions

- [ ] Decide whether experiments are independent or part of a multi-project build.
- [ ] Add screenshots when they materially aid an experiment.
