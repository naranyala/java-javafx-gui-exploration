package dev.exploration.starter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TypedPreferencesTest {
    private Preferences node;
    private TypedPreferences store;

    @BeforeEach
    void setUp() {
        node = Preferences.userRoot().node("javafx-exploration-test-" + UUID.randomUUID());
        store = new TypedPreferences(node);
    }

    @AfterEach
    void tearDown() throws BackingStoreException {
        node.removeNode();
        Preferences.userRoot().flush();
    }

    @Test
    void missingValueUsesDefault() {
        var key = PreferenceKey.integerKey("count", 3, value -> value >= 0);
        assertEquals(3, store.get(key));
    }

    @Test
    void integerCodecRoundTripsAndFlushes() throws BackingStoreException {
        var key = PreferenceKey.integerKey("count", 3, value -> value >= 0);
        store.put(key, Integer.MAX_VALUE);
        store.flush();
        assertEquals(Integer.MAX_VALUE, store.get(key));
    }

    @Test
    void malformedOrRejectedStoredValuesFallBackToDefault() {
        var key = PreferenceKey.integerKey("count", 3, value -> value >= 0);
        node.put("count", "not-an-int");
        assertEquals(3, store.get(key));
        node.put("count", "-1");
        assertEquals(3, store.get(key));
    }

    @Test
    void invalidWritesAreRejectedAndRemoveRestoresDefault() {
        var key = PreferenceKey.integerKey("count", 3, value -> value >= 0);
        assertThrows(IllegalArgumentException.class, () -> store.put(key, -1));
        store.put(key, 4);
        store.remove(key);
        assertEquals(3, store.get(key));
    }

    @Test
    void stringValuesRoundTrip() {
        var key = PreferenceKey.stringKey("theme", "system");
        store.put(key, "dark");
        assertEquals("dark", store.get(key));
    }
}
