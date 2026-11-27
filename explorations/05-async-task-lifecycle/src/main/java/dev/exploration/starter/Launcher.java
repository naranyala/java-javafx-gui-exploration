package dev.exploration.starter;

/**
 * Plain entry point for packaging tools.
 *
 * <p>Some launchers (notably jpackage) expect a main class that does not extend
 * {@code Application}. Keeping this tiny class avoids that limitation and keeps
 * {@link App} free to focus on the UI.
 */
public final class Launcher {

    private Launcher() {
    }

    public static void main(String[] args) {
        App.main(args);
    }
}
