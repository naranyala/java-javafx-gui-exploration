# Explorations

Each folder here is a **small, self-contained experiment** focused on one
JavaFX topic.

## Naming

```text
NN-short-topic/
```

Examples: `01-layouts-and-controls/`, `04-css-theming/`.

## Requirements

Every exploration must:

1. Be runnable with one documented command.
2. Include a `README.md` with:
   - **Goal** — what question this answers
   - **What to try** — steps or interactions to observe
   - **Result** — what happened, including surprises
   - **Takeaway** — the reusable lesson
3. Avoid depending on machine-specific paths or unpublished artifacts.
4. Prefer the smallest example that demonstrates the idea.

## Current experiments

- `05-async-task-lifecycle/` — JavaFX `Task` baseline and lifecycle questions.
- `06-controller-lifecycle/` — includes a small `SubscriptionScope` prototype for deterministic cleanup.
- `07-typed-preferences/` — includes a typed facade over JDK `Preferences` with defaults and validation.

These are initial prototypes, not library recommendations. Record test results and known limitations in each experiment before considering extraction.

## Starting a new exploration

Copy a template and rename it:

```bash
cp -r templates/maven-starter explorations/01-my-topic
```

Then update the Maven `artifactId` (or Gradle project name), package name, window title, and README. Remove the demo controls and background task if they are unrelated to the experiment; keep each exploration focused.

Keep experiment-specific code out of `templates/`; the starters should stay a
clean baseline.
