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
        Objects.requireNonNull(key, "key");
        String stored = preferences.get(key.name(), null);
        if (stored == null) return key.defaultValue();
        final T decoded;
        try {
            decoded = key.codec().decode(stored);
        } catch (RuntimeException malformed) {
            return key.defaultValue();
        }
        return key.valid().test(decoded) ? decoded : key.defaultValue();
    }

    public <T> void put(PreferenceKey<T> key, T value) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");
        if (!key.valid().test(value)) throw new IllegalArgumentException("Invalid value for " + key.name());
        String encoded = Objects.requireNonNull(key.codec().encode(value), "codec returned null");
        preferences.put(key.name(), encoded);
    }

    public void remove(PreferenceKey<?> key) {
        preferences.remove(Objects.requireNonNull(key, "key").name());
    }

    /** Persist pending changes; callers choose when the potentially blocking flush occurs. */
    public void flush() throws java.util.prefs.BackingStoreException {
        preferences.flush();
    }
}
