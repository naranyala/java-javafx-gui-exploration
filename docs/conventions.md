# Experiment conventions

Experiments are small, independent investigations of one JavaFX question. The purpose is learning and comparison, not building a production application.

## Folder and build

Use a numbered, descriptive folder name such as `01-layouts-and-controls/`. Copy either starter from `templates/` into `explorations/`, then change its project name, package, window title, and documentation. Keep an experiment runnable without dependencies on unpublished artifacts or machine-specific paths.

Prefer one build system per experiment. The repository provides Maven and Gradle baselines for comparison; an experiment need not maintain duplicate builds unless that comparison is its subject.

## README for each experiment

Include:

1. **Goal** — the question or behavior under study.
2. **Run** — prerequisites and the exact command.
3. **Try** — concrete interactions or variations.
4. **Observe** — outcomes, including unexpected behavior.
5. **Takeaway** — the reusable lesson and relevant limitations.

Keep the example minimal and remove starter UI that does not serve the topic. Add implementation details in source comments only when they help explain a non-obvious choice. Put cross-cutting verified discoveries in [Findings](findings.md).
