# Backlog

Prioritized work for the JavaFX learning repository. Check off only work implemented and verified; record verification details in `docs/findings.md`.

## Next: reproducible baseline

- [ ] Add Maven and Gradle wrappers and pin tested versions.
- [x] Add CI to build both starters on pushes and pull requests (GitHub Actions; pending remote run).
- [ ] Add lightweight tests for starter logic; keep GUI tests separate until headless strategy is validated.
- [ ] Document tested JDK/JavaFX/OS combinations and refresh stale environment notes.

## Starter improvements

- [ ] Align dependency and plugin versions across templates where practical.
- [x] Add accessible names, keyboard/focus guidance, and an accessibility checklist (FXML parsed; platform screen-reader/focus testing remains a follow-up).
- [ ] Define a consistent app title and icon convention.
- [ ] Choose basic logging and exception-reporting conventions.

## Learning experiments

Keep each experiment focused and runnable. Record the standard JavaFX/JDK baseline, alternatives tried, observed limitations, and a short takeaway. Treat an experiment as evidence—not an automatic reason to create a library. For library-oriented follow-up, see the [companion-libs research workflow](https://github.com/naranyala/javafx-companion-libs/blob/main/docs/experiment-to-library.md).

- [ ] Layouts and controls: sizing, resizing, and common layout panes.
- [ ] FXML: nested views, controller communication, and imperative equivalent.
- [ ] Binding/MVVM: properties, computed/bidirectional bindings, validation.
- [ ] CSS: runtime themes, pseudo-classes, looked-up colors.
- [ ] **Async task lifecycle:** extend the starter `Task` demo with cancellation, failure, progress, stale-result suppression, and safe FX-thread delivery; compare with `Service` and `CompletableFuture`. Initial baseline: [`05-async-task-lifecycle`](explorations/05-async-task-lifecycle/).
- [ ] **View/controller lifecycle:** create and dispose views repeatedly; compare listener/subscription cleanup patterns and document leak risks. Initial `SubscriptionScope` prototype: [`06-controller-lifecycle`](explorations/06-controller-lifecycle/).
- [ ] **JavaFX test setup:** evaluate JavaFX startup/thread fixtures and async completion; verify display/headless requirements and CI reliability before adopting GUI tests.
- [ ] **Typed preferences:** prototype typed keys, defaults, validation, persistence, and migration; compare JDK `Preferences`/`Properties` and keep JavaFX binding optional. Initial typed JDK `Preferences` facade: [`07-typed-preferences`](explorations/07-typed-preferences/).
- [ ] Data views: editable tables, custom cells, filtering/sorting.
- [ ] Charts, media, and `WebView`; document platform limitations.
- [ ] For promising experiments, record alternatives, failure modes, compatibility constraints, and a go/no-go or follow-up decision; link that evidence from the companion-libs research brief when relevant.

## Testing and distribution

- [ ] Evaluate TestFX and headless/CI options; add a GUI smoke test if reliable.
- [ ] Compare classpath/module-path approaches, including FXML reflection.
- [ ] Build a `jlink` runtime image and `jpackage` installer; document native dependencies.

## Repository decisions

- [ ] Decide whether experiments are independent or part of a multi-project build.
- [ ] Add screenshots when they materially aid an experiment.
