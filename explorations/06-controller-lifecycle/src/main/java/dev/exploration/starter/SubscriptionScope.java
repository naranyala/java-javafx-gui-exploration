package dev.exploration.starter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/** Owns cleanup actions for resources whose lifetime is a view/controller. */
public final class SubscriptionScope implements AutoCloseable {
    private final Deque<AutoCloseable> resources = new ArrayDeque<>();
    private boolean closed;

    /** Register a resource; if already closed, close it immediately. */
    public synchronized <T extends AutoCloseable> T own(T resource) {
        Objects.requireNonNull(resource, "resource");
        if (closed) {
            closeQuietly(resource);
        } else {
            resources.push(resource);
        }
        return resource;
    }

    @Override
    public synchronized void close() {
        if (closed) return;
        closed = true;
        RuntimeException failure = null;
        while (!resources.isEmpty()) {
            try {
                resources.pop().close();
            } catch (Exception e) {
                if (failure == null) failure = new RuntimeException("Failed to close owned resource");
                failure.addSuppressed(e);
            }
        }
        if (failure != null) throw failure;
    }

    private static void closeQuietly(AutoCloseable resource) {
        try { resource.close(); } catch (Exception e) {
            throw new RuntimeException("Resource registered after scope was closed", e);
        }
    }
}
