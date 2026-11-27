# Async task lifecycle

## Goal

Explore cancellation, failure, progress, and stale-result suppression with JavaFX `Task`, without introducing a task framework.

## What to try

Run `mvn javafx:run`. Start the task and observe progress/status; press Cancel during execution to test cooperative cancellation. Select “Demonstrate failure” before starting to trigger a predictable worker exception halfway through. The run button is disabled while work is active, so this UI does not yet exercise overlapping/latest-wins requests.

## Result

The UI exposes success, cancellation, and deliberate failure terminal paths; `Task` delivers terminal handlers on the JavaFX Application Thread. Cancellation is cooperative: worker code checks `isCancelled()`, while `Thread.sleep` is interruptible and may throw `InterruptedException`. This demo serializes requests by disabling Run while busy, so stale-result suppression is not implemented or tested. A generation token is a possible policy when newer requests supersede older work.

## Takeaway

Prefer `Task`/`Service` for JavaFX work unless experiments show a specific composition gap. Keep UI state changes in FX-thread handlers, handle all terminal states, and explicitly define whether old results may still be applied.

## Baseline and follow-up

Compare with `Service` and `CompletableFuture` for cancellation, progress reporting, executor ownership, error delivery, and stale-result behavior. This experiment is exploratory; it does not justify a reusable library by itself.
