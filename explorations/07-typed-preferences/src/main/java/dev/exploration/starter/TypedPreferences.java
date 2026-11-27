package dev.exploration.starter;

import java.util.Objects;
import java.util.prefs.Preferences;

/** Small typed facade over JDK Preferences; invalid/malformed stored values fall back. */
public final class TypedPreferences {
    private final Preferences preferences;

    public TypedPreferences(Preferences preferences) {
        this.preferences = Objects.requireNonNull(preferences, "preferences");
    }

    public <T> T get(PreferenceKey<T> key) {
        String stored = preferences.get(key.name(), null);
        if (stored == null) return key.defaultValue();
        try {
            T decoded = key.codec().decode(stored);
            return key.valid().test(decoded) ? decoded : key.defaultValue();
        } catch (RuntimeException malformed) {
            return key.defaultValue();
        }
    }

    public <T> void put(PreferenceKey<T> key, T value) {
        Objects.requireNonNull(value, "value");
        if (!key.valid().test(value)) throw new IllegalArgumentException("Invalid value for " + key.name());
        preferences.put(key.name(), key.codec().encode(value));
    }

    public void remove(PreferenceKey<?> key) {
        preferences.remove(key.name());
    }
}
