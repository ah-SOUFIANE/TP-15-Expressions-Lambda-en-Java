package com.tp15.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparateurCompose {
    public static void main(String[] args) {
        List<PersonneTri> personnes = Arrays.asList(
            new PersonneTri("Dupont", "Jean", 30),
            new PersonneTri("Dupont", "Alice", 25),
            new PersonneTri("Dupont", "Jean", 20),
            new PersonneTri("Martin", "Pierre", 40)
        );

        Comparator<PersonneTri> comparateur = Comparator
            .comparing(PersonneTri::getNom)
            .thenComparing(PersonneTri::getPrenom)
            .thenComparingInt(PersonneTri::getAge);

        personnes.sort(comparateur);

        System.out.println("Personnes triées :");
        personnes.forEach(System.out::println);
    }
}