package com.cubiculus.api.client;

import java.util.Optional;
import java.util.function.Consumer;

public class Possibly<T> {

    final T value;
    final Exception exception;

    private Possibly(T value) {
        if (value == null)
            throw new RuntimeException("value of Possibly cannot be null");
        this.value = value;
        this.exception = null;
    }

    private Possibly(Exception exception) {
        if (exception == null)
            throw new RuntimeException("exception of Possibly cannot be null");
        this.value = null;
        this.exception = exception;
    }

    public static <T> Possibly<T> of(T value) {
        return new Possibly(value);
    }

    public static <T> Possibly<T> of(Exception exception) {
        return new Possibly(exception);
    }

    public boolean is() {
        return value != null;
    }

    public Possibly<T> doIfException(Consumer<Exception> action) {
        if (exception != null) {
            action.accept(exception);
        }
        return this;
    }

    public Optional<T> getValue() {
        return Optional.ofNullable(value);
    }

    public Optional<Exception> getException() {
        return Optional.ofNullable(exception);
    }

}
