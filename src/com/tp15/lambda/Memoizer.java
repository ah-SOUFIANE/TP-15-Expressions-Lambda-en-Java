package com.tp15.lambda;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Memoizer {

    private static Function<Integer, Long> fibonacci = n -> {
        if (n <= 1) return (long) n;
        System.out.println("Calcul de fibonacci(" + n + ")");
        return fibonacci.apply(n - 1) + fibonacci.apply(n - 2);
    };

    private static Function<Integer, Long> fibonacciMemoized = memoize(n -> {
        if (n <= 1) return (long) n;
        System.out.println("Calcul de fibonacci(" + n + ")");
        return fibonacciMemoized.apply(n - 1) + fibonacciMemoized.apply(n - 2);
    });

    public static void main(String[] args) {
        System.out.println("Version non-memoized:");
        long start = System.currentTimeMillis();
        System.out.println("fibonacci(10) = " + fibonacci.apply(10));
        System.out.println("Temps: " + (System.currentTimeMillis() - start) + "ms");
        
        System.out.println("\nVersion memoized:");
        start = System.currentTimeMillis();
        System.out.println("fibonacci(10) = " + fibonacciMemoized.apply(10));
        System.out.println("Temps: " + (System.currentTimeMillis() - start) + "ms");
        
        System.out.println("\nDeuxième appel memoized:");
        start = System.currentTimeMillis();
        System.out.println("fibonacci(10) = " + fibonacciMemoized.apply(10));
        System.out.println("Temps: " + (System.currentTimeMillis() - start) + "ms");
    }
    
    public static <T, R> Function<T, R> memoize(Function<T, R> function) {
        Map<T, R> cache = new ConcurrentHashMap<>();
        return input -> cache.computeIfAbsent(input, function);
    }
}