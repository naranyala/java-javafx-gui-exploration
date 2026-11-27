package dev.exploration.starter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class SubscriptionScopeTest {
    @Test
    void closesOnceInReverseRegistrationOrder() {
        SubscriptionScope scope = new SubscriptionScope();
        List<Integer> closed = new ArrayList<>();
        scope.own(() -> closed.add(1));
        scope.own(() -> closed.add(2));

        scope.close();
        scope.close();

        assertEquals(List.of(2, 1), closed);
    }

    @Test
    void closesLateRegistrationImmediately() {
        SubscriptionScope scope = new SubscriptionScope();
        scope.close();
        List<String> closed = new ArrayList<>();
        AutoCloseable resource = () -> closed.add("closed");

        assertSame(resource, scope.own(resource));
        assertEquals(List.of("closed"), closed);
    }

    @Test
    void attemptsAllCleanupAndAggregatesFailures() {
        SubscriptionScope scope = new SubscriptionScope();
        List<Integer> closed = new ArrayList<>();
        Exception first = new Exception("first");
        Exception second = new Exception("second");
        scope.own(() -> closed.add(1));
        scope.own(() -> { throw first; });
        scope.own(() -> { throw second; });

        RuntimeException failure = assertThrows(RuntimeException.class, scope::close);

        assertEquals(List.of(1), closed);
        assertEquals(2, failure.getSuppressed().length);
        assertSame(second, failure.getSuppressed()[0]);
        assertSame(first, failure.getSuppressed()[1]);
    }

    @Test
    void cleanupMayRegisterAnotherResourceWithoutDeadlockingOrCorruptingScope() {
        SubscriptionScope scope = new SubscriptionScope();
        List<String> closed = new ArrayList<>();
        scope.own(() -> {
            closed.add("outer");
            scope.own(() -> closed.add("late"));
        });
        scope.own(() -> closed.add("first"));

        scope.close();

        assertEquals(List.of("first", "outer", "late"), closed);
    }
}
