package com.tp15.lambda;

import java.io.IOException;
import java.util.function.Function;

@FunctionalInterface
public interface IOFunction<T, R> {
    R apply(T t) throws IOException;
    
    static <T, R> Function<T, R> unchecked(IOFunction<T, R> f) {
        return t -> {
            try {
                return f.apply(t);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}