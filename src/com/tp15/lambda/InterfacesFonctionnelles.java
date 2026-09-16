package com.tp15.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class InterfacesFonctionnelles {
    public static void main(String[] args) {
        Predicate<String> estVide = s -> s.isEmpty();
        System.out.println("La chaîne est vide: " + estVide.test(""));
        System.out.println("La chaîne est vide: " + estVide.test("Bonjour"));
        
        Consumer<String> afficheur = s -> System.out.println("Affichage: " + s);
        afficheur.accept("Bonjour le monde");
        
        Function<String, Integer> longueur = s -> s.length();
        System.out.println("Longueur: " + longueur.apply("Bonjour"));
        
        Supplier<Double> nombreAleatoire = () -> Math.random();
        System.out.println("Nombre aléatoire: " + nombreAleatoire.get());
    }
}