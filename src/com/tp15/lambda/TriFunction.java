package com.tp15.lambda;

import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
    
    default <V> TriFunction<A, B, C, V> andThen(Function<? super R, ? extends V> after) {
        return (a, b, c) -> after.apply(apply(a, b, c));
    }
}