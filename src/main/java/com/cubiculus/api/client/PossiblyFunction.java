package com.cubiculus.api.client;

import java.util.function.Function;

public class PossiblyFunction<V, R> implements Function<V, Possibly<R>> {
    private final ExceptionFunction<V, R> f;

    private PossiblyFunction(final ExceptionFunction<V, R> f) {
        this.f = f;
    }

    static <V, R> PossiblyFunction<V, R> of(final ExceptionFunction<V, R> f) {
        return new PossiblyFunction<>(f);
    }

    @Override
    public Possibly<R> apply(V value) {
        try {
            return Possibly.of(f.apply(value));
        } catch (Exception e) {
            return Possibly.of(e);
        }
    }
}
