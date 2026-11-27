# Typed preferences

## Goal

Compare typed application settings with the JDK's `Preferences` and `Properties`, and identify what validation, defaults, migrations, and persistence semantics an application actually needs.

## What to try

Run `mvn javafx:run` for the starter and `mvn verify` for the pure-Java tests. Start with a typed key/default model, validate values before writing, and test restart persistence. Compare `Preferences` (platform/user scoped) with a `Properties` file (explicit file format/location); do not bind UI controls directly to storage.

## Result

A typed wrapper can centralize defaults and validation, but it still stores strings by key name, so callers can reuse a name with incompatible key types. Malformed codec data falls back to the default; invalid writes are rejected; storage exceptions propagate. `flush()` is explicit because it may block. Migrations, atomic file writes, and concurrent access policy remain unresolved. JavaFX properties are optional and not part of this storage facade.

## Takeaway

First establish a concrete persistence requirement and failure model. Prefer existing JDK storage when sufficient; a reusable abstraction needs a demonstrated gap and tests for defaults, invalid values, migration, and failed writes.
