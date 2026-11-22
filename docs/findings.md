# Findings

Running notes from the JavaFX exploration. Add dated entries; keep them short
and actionable.

## 2026-11-22 — Toolchain baseline

**Environment**

- AlmaLinux 10.2, x86_64, Wayland session with XWayland (`DISPLAY=:0`)
- Temurin JDK 21.0.13.1 LTS at `~/.local/opt/jdk-21`
- JavaFX SDK 21.0.13 at `~/.local/opt/javafx-sdk-21`
- Maven 3.9.16, Gradle 9.3.1

**Verified**

- `mvn javafx:run` opens a window (JavaFX artifacts resolved from Maven Central)
- `gradle run` with `org.openjfx.javafxplugin` 0.1.0 opens the same window
- Raw SDK works with
  `javac --module-path "$JAVAFX_HOME/lib" --add-modules javafx.controls,javafx.fxml`
- GTK3, OpenGL, X11/Xtst, fontconfig, and ALSA libraries are all present

**Notes**

- No JDK was installed system-wide; the toolchain lives in user space.
- `JAVA_HOME` and `JAVAFX_HOME` are exported from `~/.bashrc` and `~/.profile`.
- `~/.local/bin` contains JDK, Maven, and Gradle launchers, so the tools resolve
  from any working directory.
- Maven Central is fast; the Gluon JavaFX SDK download was slow but usable.
- The starter uses classpath mode (no `module-info.java`). FXML reflection works
  without `--add-opens` because the application is in the unnamed module.
