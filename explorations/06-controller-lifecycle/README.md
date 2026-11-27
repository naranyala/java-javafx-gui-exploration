# Controller and subscription lifecycle

## Goal

Make ownership and cleanup of listeners explicit when JavaFX views/controllers are created and discarded.

## What to try

Run `mvn javafx:run` to inspect the starter, and run `mvn verify` for the pure-Java `SubscriptionScope` tests. Add a listener to a long-lived observable (for example, a static or application-level property), navigate away/recreate the view repeatedly, and observe whether the old controller remains reachable or continues receiving updates. Compare explicit listener removal with JavaFX binding disposal.

## Result

Bindings created with `target.bind(source)` are owned by the target relationship and should be unbound when the target/view is disposed if the source outlives it. Manually registered listeners require retaining the exact listener instance and removing it. A controller lifecycle hook is application-specific; FXMLLoader does not provide a universal disposal contract.

## Takeaway

Keep a clear owner for each listener/subscription and provide deterministic cleanup at the view boundary. The prototype closes once in reverse registration order, runs cleanup outside its lock, and immediately closes late registrations; concurrent close callers return once closure has begun, without waiting for cleanup to finish. Verify JavaFX listener adapters and repeated view creation/disposal before proposing a general scope abstraction.
