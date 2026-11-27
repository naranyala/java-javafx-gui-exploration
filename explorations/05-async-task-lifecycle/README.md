# Async task lifecycle

## Goal

Explore cancellation, failure, progress, and stale-result suppression with JavaFX `Task`, without introducing a task framework.

## What to try

Run `mvn javafx:run`. Start the demo task, observe progress/status, then start another operation only after the previous one finishes. The starter's `Task` demo is the baseline; extend its worker with a deliberate failure and a cancellation control to compare the terminal handlers.

## Result

`Task` delivers state changes and terminal handlers on the JavaFX Application Thread. Cancellation is cooperative: worker code must check `isCancelled()` and interruptible blocking operations may throw `InterruptedException`. A generation token is needed when a newer request should make older results irrelevant; cancelling alone does not guarantee that an uncooperative worker stops immediately.

## Takeaway

Prefer `Task`/`Service` for JavaFX work unless experiments show a specific composition gap. Keep UI state changes in FX-thread handlers, handle all terminal states, and explicitly define whether old results may still be applied.

## Baseline and follow-up

Compare with `Service` and `CompletableFuture` for cancellation, progress reporting, executor ownership, error delivery, and stale-result behavior. This experiment is exploratory; it does not justify a reusable library by itself.
