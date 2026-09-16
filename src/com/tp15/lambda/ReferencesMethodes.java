package com.tp15.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ReferencesMethodes {
    public static void main(String[] args) {
        Function<String, Integer> parser = Integer::parseInt;
        System.out.println("Parsing: " + parser.apply("123"));
        
        Consumer<String> printer = System.out::println;
        printer.accept("Via référence de méthode");
        
        BiFunction<String, String, Boolean> comparateur = String::equalsIgnoreCase;
        System.out.println("Comparaison: " + comparateur.apply("hello", "HELLO"));
        
        Supplier<List<String>> listFactory = ArrayList::new;
        List<String> liste = listFactory.get();
        liste.add("élément");
        System.out.println("Liste créée: " + liste);
        
        Function<Integer, String[]> arrayCreator = String[]::new;
        String[] array = arrayCreator.apply(5);
        array[0] = "Premier";
        System.out.println("Taille du tableau: " + array.length);
    }
}