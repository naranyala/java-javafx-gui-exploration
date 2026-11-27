# Typed preferences

## Goal

Compare typed application settings with the JDK's `Preferences` and `Properties`, and identify what validation, defaults, migrations, and persistence semantics an application actually needs.

## What to try

Run `mvn javafx:run` for the starter, then replace its demo controller with a small settings screen. Start with a typed key/default model, validate values before writing, and test restart persistence. Compare `Preferences` (platform/user scoped) with a `Properties` file (explicit file format/location); do not bind UI controls directly to storage.

## Result

A typed wrapper can prevent key/type mismatches and centralize defaults, but migrations, atomic writes, storage failure handling, and synchronization are separate concerns. JavaFX properties are useful as an optional UI adapter, not a requirement for a storage core.

## Takeaway

First establish a concrete persistence requirement and failure model. Prefer existing JDK storage when sufficient; a reusable abstraction needs a demonstrated gap and tests for defaults, invalid values, migration, and failed writes.
