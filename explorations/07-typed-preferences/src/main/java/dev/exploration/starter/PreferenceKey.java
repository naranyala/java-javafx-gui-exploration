package dev.exploration.starter;

import java.util.Objects;
import java.util.function.Predicate;

/** A typed preference key with a fallback value and validation rule. */
public record PreferenceKey<T>(String name, T defaultValue, Codec<T> codec, Predicate<T> valid) {
    public PreferenceKey {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name must not be blank");
        Objects.requireNonNull(defaultValue, "defaultValue");
        Objects.requireNonNull(codec, "codec");
        Objects.requireNonNull(valid, "valid");
        if (!valid.test(defaultValue)) throw new IllegalArgumentException("default value is invalid");
    }

    public interface Codec<T> {
        String encode(T value);
        T decode(String value);
    }

    public static PreferenceKey<String> stringKey(String name, String fallback) {
        return new PreferenceKey<>(name, fallback, new Codec<>() {
            public String encode(String value) { return value; }
            public String decode(String value) { return value; }
        }, value -> true);
    }

    public static PreferenceKey<Integer> integerKey(String name, int fallback, Predicate<Integer> valid) {
        return new PreferenceKey<>(name, fallback, new Codec<>() {
            public String encode(Integer value) { return value.toString(); }
            public Integer decode(String value) { return Integer.valueOf(value); }
        }, valid);
    }
}
