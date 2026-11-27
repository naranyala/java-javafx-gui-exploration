package dev.exploration.starter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/** Owns cleanup actions for resources whose lifetime is a view/controller. */
public final class SubscriptionScope implements AutoCloseable {
    private final Deque<AutoCloseable> resources = new ArrayDeque<>();
    private boolean closed;

    /**
     * Register a resource; if the scope is already closed, close it immediately.
     * Cleanup is always invoked outside the scope lock.
     */
    public <T extends AutoCloseable> T own(T resource) {
        Objects.requireNonNull(resource, "resource");
        boolean closeNow;
        synchronized (this) {
            closeNow = closed;
            if (!closeNow) resources.push(resource);
        }
        if (closeNow) closeLateResource(resource);
        return resource;
    }

    /**
     * Closes resources once, in reverse registration order. The closed state is
     * published before cleanup starts, so reentrant/late registrations close
     * immediately. Concurrent callers after closure begins return immediately.
     */
    @Override
    public void close() {
        Deque<AutoCloseable> toClose;
        synchronized (this) {
            if (closed) return;
            closed = true;
            toClose = new ArrayDeque<>(resources);
            resources.clear();
        }

        RuntimeException failure = null;
        while (!toClose.isEmpty()) {
            try {
                toClose.pop().close();
            } catch (Exception e) {
                if (failure == null) failure = new RuntimeException("Failed to close owned resource");
                failure.addSuppressed(e);
            }
        }
        if (failure != null) throw failure;
    }

    private static void closeLateResource(AutoCloseable resource) {
        try {
            resource.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to close resource registered after scope was closed", e);
        }
    }
}
